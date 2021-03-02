using System;
using System.Collections.Generic;
using System.Text;
using LibSIE.IO.Iff;
using LibSIE;

namespace STFPlugin
{
    public class StringFile : IIffSerialisable
    {
        byte Version;
        public uint NextUID;
        public List<StringEntry> Entries = new List<StringEntry>();

        public const uint ABCD = 0xabcd;

        public StringFile()
        {
            Entries = new List<StringEntry>();
            Version = 1;
            NextUID = 1;
        }

        public StringFile(IffStream iffStream)
        {
            ReadObject(iffStream);
        }

        public void ReadObject(IffStream iffStream)
        {
            uint abcd = iffStream.GetUInt32();
            if (abcd != ABCD)
            {
                return;
            }

            Version = iffStream.GetByte();
            NextUID = iffStream.GetUInt32();
            uint numString = iffStream.GetUInt32();

            Dictionary<uint, string> ht = new Dictionary<uint, string>();
            for (uint i = 0; i < numString; i++)
            {
                uint num = iffStream.GetUInt32();
                uint key = iffStream.GetUInt32();
                string str = Encoding.Unicode.GetString(iffStream.GetBytes(2 * iffStream.GetUInt32()));
                ht.Add(num, str);
            }

            Entries.Clear();
            for (int j = 0; j < numString; j++)
            {
                uint stfKey = iffStream.GetUInt32();
                uint strLen = iffStream.GetUInt32();
                Entries.Add(new StringEntry(iffStream.GetString(strLen), ht[stfKey]));
            }
        }

        public void WriteObject(IffWriter iffStream)
        {
            iffStream.Write(0xabcdu);
            iffStream.Write(Version);
            iffStream.Write(NextUID);
            iffStream.Write(Entries.Count);

            int index = 1;
            foreach (var v in Entries)
            {
                iffStream.Write(index++);
                iffStream.Write(0xFFFFFFFFu);
                iffStream.Write(v.Value.Length);
                iffStream.Write(Encoding.Unicode.GetBytes(v.Value));
            }

            index = 1;
            foreach (var v in Entries)
            {
                iffStream.Write(index++);
                iffStream.Write(v.ID.Length);
                iffStream.Write(Encoding.ASCII.GetBytes(v.ID));
            }
        }
    }
}
