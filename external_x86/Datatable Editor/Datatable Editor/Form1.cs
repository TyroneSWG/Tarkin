using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Datatable_Editor
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            
        }



        static IEnumerable<string> ReadAsLines(string filename)
        {
            if (filename == null)
            {
                yield return "nil value";
            }
            using (var reader = new StreamReader(filename))
                while (!reader.EndOfStream)
                    yield return reader.ReadLine();
        }

        public DataTable Main(string filename)
        {
            var reader = ReadAsLines(filename);

            var data = new DataTable();

            //this assume the first record is filled with the column names
            var headers = reader.First().Split('\t');
            foreach (var header in headers)
                data.Columns.Add(header);

            var records = reader.Skip(1);
            foreach (var record in records)
                data.Rows.Add(record.Split('\t'));

            return data;
        }

        private void browseButton_Click(object sender, EventArgs e)
        {
            int size = -1;
            DialogResult result = openFileDialog1.ShowDialog(); // Show the dialog.
            if (result == DialogResult.OK) // Test result.
            {
                string file = openFileDialog1.FileName;
                richTextBox1.Text = file;
                try
                {
                    string text = File.ReadAllText(file);
                    size = text.Length;
                    //richTextBox1.Text = text;
                }
                
                catch (IOException)
                {
                }
            }
            
            Console.WriteLine(size); // <-- Shows file size in debugging mode.
            Console.WriteLine(result); // <-- For debugging use.
        }

        private static string GetTextFromDataTable(DataTable dataTable)
        {
            var stringBuilder = new StringBuilder();
            stringBuilder.AppendLine(string.Join("\t", dataTable.Columns.Cast<DataColumn>().Select(arg => arg.ColumnName)));
            foreach (DataRow dataRow in dataTable.Rows)
                stringBuilder.AppendLine(string.Join("\t", dataRow.ItemArray.Select(arg => arg.ToString())));
            return stringBuilder.ToString();
        }
        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            foreach (DataGridViewColumn col in dataGridView1.Columns)
            {
                col.SortMode = DataGridViewColumnSortMode.NotSortable;
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void loadData_Click(object sender, EventArgs e)
        {

            string texty = richTextBox1.Text;

            dataGridView1.DataSource = Main(texty);

        }

        private void saveData_Click(object sender, EventArgs e)
        {
            DataSet ds = new DataSet();
            DataTable dt = new DataTable();
            dt = ((DataTable)dataGridView1.DataSource);
            ds.Tables.Add(dt);


            
            var text = GetTextFromDataTable(ds.Tables[0]);
            File.Copy(richTextBox1.Text, richTextBox1.Text + ".backup", true);
            File.WriteAllText(richTextBox1.Text, text);

            dt.Clear();
            ds.Clear();
            loadData_Click(null,null);

        }

        private void searchButton_Click(object sender, EventArgs e)
        {
            string searchValue = richTextBox2.Text;
            dataGridView1.SelectionMode = DataGridViewSelectionMode.FullRowSelect;
            foreach (DataGridViewColumn col in dataGridView1.Columns)
            {
                col.SortMode = DataGridViewColumnSortMode.NotSortable;
            }
            try
            {
                dataGridView1.ClearSelection();
                bool valueResult = false;
                foreach (DataGridViewRow row in dataGridView1.Rows)
                {
                    for (int i = 0; i < row.Cells.Count; i++)
                    {
                        if (row.Cells[i].Value != null && row.Cells[i].Value.ToString().ToLower().Equals(searchValue.ToLower()))
                        {
                            int rowIndex = row.Index;
                            dataGridView1.Rows[rowIndex].Selected = true;
                            valueResult = true;
                            break;
                        }
                    }

                }
                if (!valueResult)
                {
                    MessageBox.Show("Unable to find " + richTextBox2.Text, "Not Found");
                    return;
                }
                dataGridView1.FirstDisplayedScrollingRowIndex = dataGridView1.SelectedRows[0].Index;
            }
            catch (Exception exc)
            {
                MessageBox.Show(exc.Message);
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            FreezeBand(dataGridView1.Rows[0]);
        }
        private static void FreezeBand(DataGridViewBand band)
        {
            band.Frozen = true;
            DataGridViewCellStyle style = new DataGridViewCellStyle();
            style.BackColor = Color.Beige;
            band.DefaultCellStyle = style;
        }
    }
}
