using System;
using System.Collections.Generic;

namespace LibSIE.Plugin
{
    /// <summary>
    /// Provides a list of key/values for labels in the status bar
    /// </summary>
    public class StatusBarController
    {
        /// <summary>
        /// Emitted when a value is changed via SetValue to notify the main window to update the status labels
        /// </summary>
        public event EventHandler ValueChanged;

        /// <param name="nameValues">The keys are finalised in the contructor, but values can be changed</param>
        public StatusBarController(Dictionary<string, string> nameValues)
        {
            this.nameValues = nameValues;
        }

        /// <summary>
        /// Use this to change status label values from your classes
        /// </summary>
        /// <param name="key"></param>
        /// <param name="value"></param>
        public void SetValue(string key, string value)
        {
            if (nameValues.ContainsKey(key))
            {
                nameValues[key] = value;
            }
            if (ValueChanged != null)
            {
                ValueChanged(this, null);
            }
        }

        /// <summary>
        /// Used by the main editor to get values - don't use it to alter values or keys!
        /// </summary>
        /// <returns></returns>
        public IDictionary<string, string> Values()
        {
            return nameValues;
        }

        Dictionary<string, string> nameValues;
    }
}
