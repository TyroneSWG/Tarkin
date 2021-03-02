namespace STFPlugin
{
    partial class FormSTF
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FormSTF));
            this.listString = new BrightIdeasSoftware.FastObjectListView();
            this.StringID = ((BrightIdeasSoftware.OLVColumn)(new BrightIdeasSoftware.OLVColumn()));
            this.StringValue = ((BrightIdeasSoftware.OLVColumn)(new BrightIdeasSoftware.OLVColumn()));
            this.toolStrip = new System.Windows.Forms.ToolStrip();
            this.cmdNew = new System.Windows.Forms.ToolStripButton();
            this.toolStripMenuItemOpen = new System.Windows.Forms.ToolStripMenuItem();
            this.cmdSaveAs = new System.Windows.Forms.ToolStripMenuItem();
            this.cmdClearKeys = new System.Windows.Forms.ToolStripMenuItem();
            this.cmdClearValues = new System.Windows.Forms.ToolStripMenuItem();
            this.tableLayoutPanel2 = new System.Windows.Forms.TableLayoutPanel();
            this.txtFilter = new System.Windows.Forms.TextBox();
            this.comboType = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.tableLayoutPanel3 = new System.Windows.Forms.TableLayoutPanel();
            this.txtAddID = new System.Windows.Forms.TextBox();
            this.cmdAdd = new System.Windows.Forms.Button();
            this.txtAddValue = new System.Windows.Forms.TextBox();
            this.openFileDialog = new System.Windows.Forms.OpenFileDialog();
            this.saveFileDialog = new System.Windows.Forms.SaveFileDialog();
            this.openFileDialogMultiTable = new System.Windows.Forms.OpenFileDialog();
            ((System.ComponentModel.ISupportInitialize)(this.listString)).BeginInit();
            this.toolStrip.SuspendLayout();
            this.tableLayoutPanel2.SuspendLayout();
            this.tableLayoutPanel1.SuspendLayout();
            this.tableLayoutPanel3.SuspendLayout();
            this.SuspendLayout();
            // 
            // listString
            // 
            this.listString.AllColumns.Add(this.StringID);
            this.listString.AllColumns.Add(this.StringValue);
            this.listString.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(40)))), ((int)(((byte)(40)))), ((int)(((byte)(40)))));
            this.listString.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.listString.CellEditActivation = BrightIdeasSoftware.ObjectListView.CellEditActivateMode.DoubleClick;
            this.listString.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.StringID,
            this.StringValue});
            this.listString.Dock = System.Windows.Forms.DockStyle.Fill;
            this.listString.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.listString.FullRowSelect = true;
            this.listString.HideSelection = false;
            this.listString.Location = new System.Drawing.Point(0, 0);
            this.listString.Margin = new System.Windows.Forms.Padding(0);
            this.listString.Name = "listString";
            this.listString.RowHeight = 30;
            this.listString.ShowGroups = false;
            this.listString.Size = new System.Drawing.Size(1039, 406);
            this.listString.TabIndex = 23;
            this.listString.UseCompatibleStateImageBehavior = false;
            this.listString.UseFiltering = true;
            this.listString.View = System.Windows.Forms.View.Details;
            this.listString.VirtualMode = true;
            this.listString.CellEditFinishing += new BrightIdeasSoftware.CellEditEventHandler(this.listString_CellEditFinishing);
            this.listString.CellEditValidating += new BrightIdeasSoftware.CellEditEventHandler(this.listCRC_CellEditValidating);
            this.listString.KeyDown += new System.Windows.Forms.KeyEventHandler(this.listCRC_KeyDown);
            // 
            // StringID
            // 
            this.StringID.AspectName = "ID";
            this.StringID.MinimumWidth = 100;
            this.StringID.Text = "ID";
            this.StringID.Width = 300;
            // 
            // StringValue
            // 
            this.StringValue.AspectName = "Value";
            this.StringValue.MinimumWidth = 100;
            this.StringValue.Text = "Value";
            this.StringValue.Width = 716;
            this.StringValue.WordWrap = true;
            // 
            // toolStrip
            // 
            this.toolStrip.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(122)))), ((int)(((byte)(204)))));
            this.toolStrip.GripStyle = System.Windows.Forms.ToolStripGripStyle.Hidden;
            this.toolStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.cmdNew,
            this.toolStripMenuItemOpen,
            this.cmdSaveAs,
            this.cmdClearKeys,
            this.cmdClearValues});
            this.toolStrip.Location = new System.Drawing.Point(0, 0);
            this.toolStrip.Name = "toolStrip";
            this.toolStrip.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
            this.toolStrip.Size = new System.Drawing.Size(1039, 25);
            this.toolStrip.TabIndex = 29;
            this.toolStrip.Text = "toolStrip1";
            // 
            // cmdNew
            // 
            this.cmdNew.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.cmdNew.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.cmdNew.Name = "cmdNew";
            this.cmdNew.Size = new System.Drawing.Size(35, 22);
            this.cmdNew.Text = "New";
            this.cmdNew.Click += new System.EventHandler(this.cmdNew_Click);
            // 
            // toolStripMenuItemOpen
            // 
            this.toolStripMenuItemOpen.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.toolStripMenuItemOpen.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
            this.toolStripMenuItemOpen.Name = "toolStripMenuItemOpen";
            this.toolStripMenuItemOpen.ShortcutKeyDisplayString = "Ctrl+O";
            this.toolStripMenuItemOpen.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.O)));
            this.toolStripMenuItemOpen.Size = new System.Drawing.Size(48, 25);
            this.toolStripMenuItemOpen.Text = "Open";
            this.toolStripMenuItemOpen.Click += new System.EventHandler(this.toolStripMenuItemOpen_Click);
            // 
            // cmdSaveAs
            // 
            this.cmdSaveAs.Enabled = false;
            this.cmdSaveAs.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.cmdSaveAs.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
            this.cmdSaveAs.Name = "cmdSaveAs";
            this.cmdSaveAs.ShortcutKeyDisplayString = "Ctrl+Shift+S";
            this.cmdSaveAs.ShortcutKeys = ((System.Windows.Forms.Keys)(((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.Shift) 
            | System.Windows.Forms.Keys.S)));
            this.cmdSaveAs.Size = new System.Drawing.Size(68, 25);
            this.cmdSaveAs.Text = "Save As...";
            this.cmdSaveAs.Click += new System.EventHandler(this.cmdSaveAs_Click);
            // 
            // cmdClearKeys
            // 
            this.cmdClearKeys.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.cmdClearKeys.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
            this.cmdClearKeys.Name = "cmdClearKeys";
            this.cmdClearKeys.ShortcutKeyDisplayString = "Ctrl+O";
            this.cmdClearKeys.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.O)));
            this.cmdClearKeys.Size = new System.Drawing.Size(72, 25);
            this.cmdClearKeys.Text = "Clear keys";
            this.cmdClearKeys.Click += new System.EventHandler(this.cmdClearKeys_Click);
            // 
            // cmdClearValues
            // 
            this.cmdClearValues.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.cmdClearValues.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
            this.cmdClearValues.Name = "cmdClearValues";
            this.cmdClearValues.ShortcutKeyDisplayString = "Ctrl+O";
            this.cmdClearValues.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.O)));
            this.cmdClearValues.Size = new System.Drawing.Size(82, 25);
            this.cmdClearValues.Text = "Clear values";
            this.cmdClearValues.Click += new System.EventHandler(this.cmdClearValues_Click);
            // 
            // tableLayoutPanel2
            // 
            this.tableLayoutPanel2.ColumnCount = 3;
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle());
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 100F));
            this.tableLayoutPanel2.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 20F));
            this.tableLayoutPanel2.Controls.Add(this.txtFilter, 1, 0);
            this.tableLayoutPanel2.Controls.Add(this.comboType, 2, 0);
            this.tableLayoutPanel2.Controls.Add(this.label1, 0, 0);
            this.tableLayoutPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel2.Location = new System.Drawing.Point(0, 529);
            this.tableLayoutPanel2.Margin = new System.Windows.Forms.Padding(0, 3, 3, 0);
            this.tableLayoutPanel2.Name = "tableLayoutPanel2";
            this.tableLayoutPanel2.RowCount = 1;
            this.tableLayoutPanel2.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel2.Size = new System.Drawing.Size(1036, 27);
            this.tableLayoutPanel2.TabIndex = 30;
            // 
            // txtFilter
            // 
            this.txtFilter.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(64)))), ((int)(((byte)(64)))), ((int)(((byte)(64)))));
            this.txtFilter.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtFilter.Dock = System.Windows.Forms.DockStyle.Fill;
            this.txtFilter.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.txtFilter.Location = new System.Drawing.Point(39, 3);
            this.txtFilter.Name = "txtFilter";
            this.txtFilter.Size = new System.Drawing.Size(894, 20);
            this.txtFilter.TabIndex = 26;
            this.txtFilter.TextChanged += new System.EventHandler(this.txtFilter_TextChanged);
            // 
            // comboType
            // 
            this.comboType.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(64)))), ((int)(((byte)(64)))), ((int)(((byte)(64)))));
            this.comboType.Dock = System.Windows.Forms.DockStyle.Fill;
            this.comboType.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.comboType.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.comboType.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.comboType.FormattingEnabled = true;
            this.comboType.Items.AddRange(new object[] {
            "Any text",
            "Prefix",
            "Regex"});
            this.comboType.Location = new System.Drawing.Point(939, 3);
            this.comboType.Name = "comboType";
            this.comboType.Size = new System.Drawing.Size(94, 21);
            this.comboType.TabIndex = 24;
            // 
            // label1
            // 
            this.label1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.label1.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.label1.Location = new System.Drawing.Point(3, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(30, 27);
            this.label1.TabIndex = 27;
            this.label1.Text = "Filter";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.ColumnCount = 1;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.Controls.Add(this.tableLayoutPanel2, 0, 2);
            this.tableLayoutPanel1.Controls.Add(this.listString, 0, 0);
            this.tableLayoutPanel1.Controls.Add(this.tableLayoutPanel3, 0, 1);
            this.tableLayoutPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel1.Location = new System.Drawing.Point(0, 25);
            this.tableLayoutPanel1.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 3;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 120F));
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 30F));
            this.tableLayoutPanel1.Size = new System.Drawing.Size(1039, 556);
            this.tableLayoutPanel1.TabIndex = 31;
            // 
            // tableLayoutPanel3
            // 
            this.tableLayoutPanel3.ColumnCount = 3;
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 30F));
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 70F));
            this.tableLayoutPanel3.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Absolute, 39F));
            this.tableLayoutPanel3.Controls.Add(this.txtAddID, 0, 0);
            this.tableLayoutPanel3.Controls.Add(this.cmdAdd, 1, 0);
            this.tableLayoutPanel3.Controls.Add(this.txtAddValue, 1, 0);
            this.tableLayoutPanel3.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel3.Location = new System.Drawing.Point(0, 406);
            this.tableLayoutPanel3.Margin = new System.Windows.Forms.Padding(0);
            this.tableLayoutPanel3.Name = "tableLayoutPanel3";
            this.tableLayoutPanel3.RowCount = 1;
            this.tableLayoutPanel3.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanel3.Size = new System.Drawing.Size(1039, 120);
            this.tableLayoutPanel3.TabIndex = 31;
            // 
            // txtAddID
            // 
            this.txtAddID.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(64)))), ((int)(((byte)(64)))), ((int)(((byte)(64)))));
            this.txtAddID.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtAddID.Dock = System.Windows.Forms.DockStyle.Fill;
            this.txtAddID.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.txtAddID.Location = new System.Drawing.Point(3, 3);
            this.txtAddID.Name = "txtAddID";
            this.txtAddID.Size = new System.Drawing.Size(294, 20);
            this.txtAddID.TabIndex = 316;
            // 
            // cmdAdd
            // 
            this.cmdAdd.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(122)))), ((int)(((byte)(204)))));
            this.cmdAdd.Dock = System.Windows.Forms.DockStyle.Left;
            this.cmdAdd.Enabled = false;
            this.cmdAdd.FlatStyle = System.Windows.Forms.FlatStyle.Popup;
            this.cmdAdd.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.cmdAdd.Location = new System.Drawing.Point(1003, 3);
            this.cmdAdd.Name = "cmdAdd";
            this.cmdAdd.Size = new System.Drawing.Size(27, 114);
            this.cmdAdd.TabIndex = 315;
            this.cmdAdd.Text = "+";
            this.cmdAdd.UseVisualStyleBackColor = false;
            this.cmdAdd.Click += new System.EventHandler(this.cmdAdd_Click);
            // 
            // txtAddValue
            // 
            this.txtAddValue.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(64)))), ((int)(((byte)(64)))), ((int)(((byte)(64)))));
            this.txtAddValue.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtAddValue.Dock = System.Windows.Forms.DockStyle.Fill;
            this.txtAddValue.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.txtAddValue.Location = new System.Drawing.Point(303, 3);
            this.txtAddValue.Multiline = true;
            this.txtAddValue.Name = "txtAddValue";
            this.txtAddValue.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.txtAddValue.Size = new System.Drawing.Size(694, 114);
            this.txtAddValue.TabIndex = 27;
            // 
            // openFileDialog
            // 
            this.openFileDialog.FileName = "openFileDialog1";
            this.openFileDialog.Filter = "String tables|*.stf";
            // 
            // saveFileDialog
            // 
            this.saveFileDialog.Filter = "String tables|*.stf";
            // 
            // openFileDialogMultiTable
            // 
            this.openFileDialogMultiTable.FileName = "openFileDialog1";
            this.openFileDialogMultiTable.Filter = "String tables|*.stf";
            this.openFileDialogMultiTable.Multiselect = true;
            this.openFileDialogMultiTable.RestoreDirectory = true;
            // 
            // FormSTF
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(32)))), ((int)(((byte)(32)))), ((int)(((byte)(32)))));
            this.ClientSize = new System.Drawing.Size(1039, 581);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Controls.Add(this.toolStrip);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ForeColor = System.Drawing.Color.WhiteSmoke;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "FormSTF";
            this.Text = "STF Plugin";
            this.Load += new System.EventHandler(this.FormSTF_Load);
            ((System.ComponentModel.ISupportInitialize)(this.listString)).EndInit();
            this.toolStrip.ResumeLayout(false);
            this.toolStrip.PerformLayout();
            this.tableLayoutPanel2.ResumeLayout(false);
            this.tableLayoutPanel2.PerformLayout();
            this.tableLayoutPanel1.ResumeLayout(false);
            this.tableLayoutPanel3.ResumeLayout(false);
            this.tableLayoutPanel3.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private BrightIdeasSoftware.FastObjectListView listString;
        private BrightIdeasSoftware.OLVColumn StringID;
        private BrightIdeasSoftware.OLVColumn StringValue;
        private System.Windows.Forms.ToolStrip toolStrip;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel2;
        private System.Windows.Forms.TextBox txtFilter;
        private System.Windows.Forms.ComboBox comboType;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItemOpen;
        private System.Windows.Forms.ToolStripButton cmdNew;
        private System.Windows.Forms.ToolStripMenuItem cmdSaveAs;
        private System.Windows.Forms.OpenFileDialog openFileDialog;
        private System.Windows.Forms.SaveFileDialog saveFileDialog;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel3;
        private System.Windows.Forms.TextBox txtAddValue;
        internal System.Windows.Forms.Button cmdAdd;
        private System.Windows.Forms.OpenFileDialog openFileDialogMultiTable;
        private System.Windows.Forms.TextBox txtAddID;
        private System.Windows.Forms.ToolStripMenuItem cmdClearKeys;
        private System.Windows.Forms.ToolStripMenuItem cmdClearValues;


    }
}