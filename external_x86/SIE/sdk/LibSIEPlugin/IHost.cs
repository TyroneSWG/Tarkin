namespace LibSIE.Plugin
{
    public interface IHost
    {
        // SIE will return null if the file did not exist
        byte[] GetFileData(string treePath);
    }
}
