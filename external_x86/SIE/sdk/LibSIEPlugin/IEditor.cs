using System;
using WeifenLuo.WinFormsUI.Docking;

namespace LibSIE.Plugin
{
    public interface IEditor : IDockContent
    {
        /// <summary>
        /// Notifies SIE if a file is supported for opening by this editor
        /// </summary>
        /// <param name="filename">The full filename "dir/blah.ext"</param>
        /// <param name="header">The first 4 bytes of the file</param>
        /// <param name="type">The main FORM type (if the file is of IFF format)</param>
        /// <returns></returns>
        bool SupportsFile(string filename, uint header, uint type);

        /// <summary>
        /// Handles a request from SIE to open a file using data from a tree file extract
        /// </summary>
        /// <param name="data">Byte array containing the data from the tree extract</param>
        /// <param name="fileName">The full filename of the tree file, e.g. appearance/vader.msh</param>
        void OpenFromTree(byte[] data, string fileName);

        /// <summary>
        /// Handles a request from SIE to open a file a system path
        /// </summary>
        /// <param name="fileName">The full path of the file, e.g. C:/appearance/vader.msh</param>
        void OpenFromFile(string fileName);

        /// <returns>The name of the current open document (could also display "New file" or something if you wish)</returns>
        string DocumentName();

        /// <summary>
        /// Should be fired whenever the DocumentName value is changed
        /// </summary>
        event EventHandler DocumentNameChanged;

        /// <returns>Whether the file being edited has any changes</returns>
        bool HasChanges();

        /// <summary>
        /// Should be fired whenever the HasChanges value is changed
        /// </summary>
        event EventHandler HasChangesChanged;

        /// <summary>
        /// The status bar controller tells the main window what labels to create in the status strip, what values to give and when to update them
        /// </summary>
        StatusBarController StatusBarController();
    }
}