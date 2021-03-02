using System;
using System.Collections.Generic;

namespace LibSIE.Plugin
{   
    public class PluginInformation
    {
        string name;
        string description;
        string author;
        string version;

        public PluginInformation(string name, string desc, string author, string version)
        {
            this.name = name;
            this.description = desc;
            this.author = author;
            this.version = version;
        }

        /// <summary>
        /// The name of the plugin will be displayed on the create menu as "New PluginName"
        /// </summary>
        public string Name { get { return name; } }

        /// <summary>
        /// The name of the plugin will be displayed on the create menu as "New PluginName"
        /// </summary>
        public string Description { get { return description; } }

        /// <summary>
        /// The author of the active plugin is shown on the top right corner of SIE to give credit
        /// </summary>
        public string Author { get { return author; } }

        /// <summary>
        ///  The version is shown next to the author and name
        /// </summary>
        public string Version { get { return version; } }
    }

    /// <summary>
    /// Plug-ins must implement this interface
    /// </summary>
    public interface IPlugin : IEditor
    {
        /// <summary>
        /// A collection of information about the plugin used to give information to the user and credit the author
        /// </summary>
        PluginInformation Information { get; }

        /// <summary>
        /// Replace the default SIE icon while using this plugin (return null to not bother)
        /// </summary>
        System.Drawing.Image PluginIcon { get; }

        /// <summary>
        /// SIE implements IHost for access to the repository open functions
        /// </summary>
        IHost Host { get; set; }

        /// <summary>
        /// Perform any start-up initialisation for the plugin
        /// </summary>
        void InitialisePlugin();

        /// <summary>
        /// Perform any garbage collection for the plugin
        /// </summary>
        void DisposePlugin();
    }
}