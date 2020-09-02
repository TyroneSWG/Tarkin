package script.item.conversion;

public class armor_bracer_r_conversion extends script.item.conversion.armor_base_conversion
{
    public armor_bracer_r_conversion()
    {
    }
    public static final String[] ARMOR_SET_ASSAULT = 
    {
        "composite/armor_composite_bracer_r.iff",
        "chitin/armor_chitin_s01_bracer_r.iff"
    };
    public static final String[] ARMOR_SET_BATTLE = 
    {
        "bone/armor_bone_s01_bracer_r.iff",
        "padded/armor_padded_s01_bracer_r.iff"
    };
    public static final String[] ARMOR_SET_RECON = 
    {
        "tantel/armor_tantel_skreej_bracer_r.iff",
        "ubese/armor_ubese_bracer_r.iff"
    };
    public static final String[] ASSAULT_TYPE = 
    {
        "Composite",
        "Chitin"
    };
    public static final String[] BATTLE_TYPE = 
    {
        "Bone",
        "Padded"
    };
    public static final String[] RECON_TYPE = 
    {
        "Tantel",
        "Ubese"
    };
    public String[] getAssaultTemplates() throws InterruptedException
    {
        return ARMOR_SET_ASSAULT;
    }
    public String[] getBattleTemplates() throws InterruptedException
    {
        return ARMOR_SET_BATTLE;
    }
    public String[] getReconTemplates() throws InterruptedException
    {
        return ARMOR_SET_RECON;
    }
    public String[] getAssaultTypes() throws InterruptedException
    {
        return ASSAULT_TYPE;
    }
    public String[] getBattleTypes() throws InterruptedException
    {
        return BATTLE_TYPE;
    }
    public String[] getReconTypes() throws InterruptedException
    {
        return RECON_TYPE;
    }
}
