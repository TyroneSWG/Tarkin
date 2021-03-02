using BrightIdeasSoftware;
using LibSIE;
using LibSIE.IO.Iff;
using LibSIE.Plugin;
using LibSIE.UI;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Reflection;
using System.Windows.Forms;
using WeifenLuo.WinFormsUI.Docking;

namespace STFPlugin
{
    public partial class FormSTF : DockContent, IPlugin
    {
        StringFile table = null;

        public FormSTF()
        {
            InitializeComponent();
            this.toolStrip.Renderer = new ToolbarRenderer();

            ObjectListView.EditorRegistry.Register(typeof(string), delegate(Object model, OLVColumn column, Object value)
            {
                if (column == StringValue)
                {
                    return new JTextBox();
                }

                TextBox t = new TextBox();
                t.Multiline = false;
                t.BackColor = Color.FromArgb(40, 40, 40);
                t.ForeColor = Color.WhiteSmoke;
                return t;
            });

            comboType.SelectedIndex = 0;
            cmdNew_Click(this, null);
        }

        public void OnOpenSuccess()
        {
            cmdAdd.Enabled = true;
            cmdSaveAs.Enabled = true;
        }

        public void OnOpenFail()
        {
            cmdAdd.Enabled = false;
            cmdSaveAs.Enabled = false;
        }

        public void SetStatusValue()
        {
            sbController.SetValue("STATUS_BAR_ENTRIES", "Entries: " + listString.GetItemCount());
        }

        public void OpenFromTree(byte[] data, string fileName)
        {
            IffStream iffStream = new IffStream(data);
            uint root = iffStream.RootType();
            if (root == StringFile.ABCD)
            {
                table = new StringFile(iffStream);
                FileName = fileName;
                FileHasChanges = false;

                listString.ClearObjects();
                this.SuspendLayout();
                listString.AddObjects(table.Entries.ToArray());
                this.ResumeLayout();

                OnOpenSuccess();
                SetStatusValue();
            }
            else
            {
                OnOpenFail();
                MessageBox.Show("This is not a string table file.", "Wrong File Type!", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }

        public void OpenFromFile(string fileName)
        {
            IffStream iffStream = new IffStream(fileName);
            uint root = iffStream.RootType();
            if (root == StringFile.ABCD)
            {
                table = new StringFile(iffStream);
                FileName = fileName;
                FileHasChanges = false;

                listString.ClearObjects();
                this.SuspendLayout();
                listString.AddObjects(table.Entries.ToArray());
                this.ResumeLayout();

                OnOpenSuccess();
                SetStatusValue();
            }
            else
            {
                MessageBox.Show("This is not a string table file.", "Wrong File Type!", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }
        
        private void cmdNew_Click(object sender, EventArgs e)
        {
            table = new StringFile();
            FileName = "New_CRC_Table.iff";
            FileHasChanges = false;

            listString.ClearObjects();
            this.SuspendLayout();
            listString.AddObjects(table.Entries.ToArray());
            this.ResumeLayout();

            OnOpenSuccess();
            SetStatusValue();
        }

        /// <summary>
        /// Definitions for IEditor
        /// </summary>
        #region EditorImplementation
        public bool HasChanges() { return m_hasChanges; }
        private bool m_hasChanges;
        public bool FileHasChanges
        {
            get { return m_hasChanges; }
            set
            {
                if (m_hasChanges != value)
                {
                    m_hasChanges = value;
                    if (HasChangesChanged != null)
                    {
                        HasChangesChanged(this, new EventArgs());
                    }
                }
            }
        }

        public string DocumentName() { return FileName; }
        string m_filename;
        public string FileName
        {
            get { return m_filename; }
            set { m_filename = value; if (DocumentNameChanged != null) { DocumentNameChanged(this, null); } }
        }

        public event EventHandler DocumentNameChanged;
        public event EventHandler HasChangesChanged;

        StatusBarController sbController = new StatusBarController(new Dictionary<string, string>()
        {
            {"STATUS_BAR_ENTRIES", "0"}
        });

        public StatusBarController StatusBarController()
        {
            return sbController;
        }

        public bool SupportsFile(string filename, uint header, uint type)
        {
            return header == StringFile.ABCD;
        }
        #endregion
        
        /// <summary>
        /// Definitions for IPlugin
        /// </summary>
        /// 
        #region PluginImplementation
        static PluginInformation info = new PluginInformation("STF Plugin", "Lasko <3", "Sytner", "0.1");

        public IHost Host { get; set; }

        public void InitialisePlugin() { }
        public void DisposePlugin() { }

        public PluginInformation Information
        {
            get { return info; }
        }

        public Image PluginIcon
        {
            get { return null; }// STFPlugin.Properties.Resources.swb32_bg.ToBitmap(); }
        }
        #endregion

        private void toolStripMenuItemOpen_Click(object sender, EventArgs e)
        {
            if (HasChanges())
            {
                if (MessageBox.Show("Are you sure you want to open another file? Your changes will not be saved.", "Question", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.No)
                {
                    return;
                }
            }

            openFileDialog.FilterIndex = 0;
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    OpenFromFile(openFileDialog.FileName);
                }
                catch (Exception exception)
                {
                    MessageBox.Show(exception.Message, Assembly.GetExecutingAssembly().GetName().Name, MessageBoxButtons.OK, MessageBoxIcon.Hand);
                }
            }
        }

        private void txtFilter_TextChanged(object sender, EventArgs e)
        {
            this.TimedFilter(listString, txtFilter.Text, comboType.SelectedIndex);
        }

        void TimedFilter(ObjectListView olv, string txt, int matchKind)
        {
            TextMatchFilter filter = null;
            if (!String.IsNullOrEmpty(txt))
            {
                switch (matchKind)
                {
                    case 0:
                    default:
                        filter = TextMatchFilter.Contains(olv, txt);
                        break;
                    case 1:
                        filter = TextMatchFilter.Prefix(olv, txt);
                        break;
                    case 2:
                        filter = TextMatchFilter.Regex(olv, txt);
                        break;
                }
            }

            if (filter == null)
            {
                olv.DefaultRenderer = null;
            }
            else
            {
                olv.DefaultRenderer = new HighlightTextRenderer(filter);
            }

            HighlightTextRenderer highlightingRenderer = olv.GetColumn(0).Renderer as HighlightTextRenderer;
            if (highlightingRenderer != null)
            {
                highlightingRenderer.Filter = filter;
            }

            olv.AdditionalFilter = filter;
        }

        private bool ValidateValues()
        {
            foreach (var v in table.Entries)
            {
                if (v.ID == "")
                {
                    MessageBox.Show("Can not have empty ID!");
                    listString.SelectedIndex = listString.IndexOf(v);
                    return false;
                }
            }

            return true;
        }

        private void cmdSaveAs_Click(object sender, EventArgs e)
        {
            if (!ValidateValues())
            {
                return;
            }
            
            if (table != null)
            {
                saveFileDialog.FilterIndex = 1;
                if (saveFileDialog.ShowDialog() == DialogResult.OK)
                {
                    IffObject.SaveToFile(table, saveFileDialog.FileName);
                    FileHasChanges = false;
                    FileName = saveFileDialog.FileName;

                    OnOpenSuccess();
                }
            }
            else
            {
                OnOpenFail();
                MessageBox.Show("No file open!", Assembly.GetExecutingAssembly().GetName().Name, MessageBoxButtons.OK, MessageBoxIcon.Hand);
            }
        }

        void AddEntry(string id, string s)
        {
            var entry = new StringEntry(id, s);
            if (!table.Entries.Contains(entry))
            {
                table.Entries.Add(entry);
                listString.AddObject(entry);
                SetStatusValue();
            }
            else
            {
                MessageBox.Show("Table already contains entry!");
            }
        }

        private void cmdAdd_Click(object sender, EventArgs e)
        {
            if (txtAddID.Text.Length != 0)
            {
                AddEntry(txtAddID.Text, txtAddValue.Text);
                FileHasChanges = true;
            }
            else
            {
                MessageBox.Show("Can not add entry with empty id!");
            }
        }

        private void listCRC_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Delete)
            {
                if (listString.SelectedObjects.Count > 0)
                {
                    FileHasChanges = true;
                }

                foreach(StringEntry entry in listString.SelectedObjects)
                {
                    table.Entries.Remove(entry);
                }

                listString.RemoveObjects(listString.SelectedObjects);
                SetStatusValue();
            }
        }

        private void listCRC_CellEditValidating(object sender, CellEditEventArgs e)
        {
            if (e.Column == StringID)
            {
                if ((string)e.NewValue == "")
                {
                    MessageBox.Show("Value can not be empty!");
                    e.Cancel = true;
                }

                foreach (var v in table.Entries)
                {
                    if (e.RowObject != v)
                    {
                        if (v.ID == (string)e.NewValue)
                        {
                            MessageBox.Show("Value already exists!");
                            e.Cancel = true;
                        }
                    }
                }
            }
        }

        private void listString_CellEditFinishing(object sender, CellEditEventArgs e)
        {
            if ((string)e.NewValue != (string)e.Value)
            {
                FileHasChanges = true;
            }
        }

        private void cmdClearValues_Click(object sender, EventArgs e)
        {
            if (listString.SelectedObjects.Count > 0)
            {
                FileHasChanges = true;
            }

            foreach (StringEntry str in listString.SelectedObjects)
            {
                str.Value = "";
            }

            listString.Refresh();
        }

        private void cmdClearKeys_Click(object sender, EventArgs e)
        {
            if (listString.SelectedObjects.Count > 0)
            {
                FileHasChanges = true;
            }

            foreach (StringEntry str in listString.SelectedObjects)
            {
                str.ID = "";
            }

            listString.Refresh();
        }

        private void FormSTF_Load(object sender, EventArgs e)
        {
            OpenFromTree(Host.GetFileData("string/en/badge_d.stf"), "string/en/badge_d.stf");
        }
    }

    class JTextBox : TextBox
    {
        public JTextBox()
        {
            this.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Multiline = true;
            this.MinimumSize = new Size(this.MinimumSize.Width, 120);
            this.BackColor = Color.FromArgb(40, 40, 40);
            this.ForeColor = Color.WhiteSmoke;
            this.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.WordWrap = true;
        }
    }
}