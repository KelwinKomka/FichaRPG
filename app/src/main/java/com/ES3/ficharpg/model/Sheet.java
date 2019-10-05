package com.ES3.ficharpg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Sheet implements Serializable {
    //Character
    private String name = "";
    private Race race;
    private CharClass charClass;
    private Background background;

    //Character Stats
    private int maxHitPoints = 1;
    private int hitPoints = 1;
    private long exp = 0L;
    private int speed = 0;
    private int proficiency = 0;
    private int initiative = 0;
    private int armor = 0;
    private boolean inspiration;

    //Attributes
    private HashMap<String, HashMap<String, Integer>> attributes;

    //Passive skills
    private int passivePerception = 0;
    private int passiveInvestigation = 0;
    private int passiveInsight = 0;

    //Proficiencies and Languages
    private String armorProficiency = "";
    private String weaponProficiency = "";
    private String toolProficiency = "";
    private ArrayList<String> languages;

    //Skills
    private HashMap<String, Integer> skills;

    //Skill proficiencies
    private HashMap<String, Boolean> skillsProficiency;

    //Defenses
    private String resistances = "";
    private String immunities = "";
    private String vulnerabilities = "";

    //Conditions
    private int exhaustion = 0;
    private HashMap<String, Boolean> conditions;

    //Equipment
    private ArrayList<Equipment> equipment;
    private LifeStyle lifestyle;
    private int copper = 0;
    private int silver = 0;
    private int electrum = 0;
    private int gold = 0;
    private int platinum = 0;
    private Double carryingCapacity = 0.0;
    private Double weightCarried = 0.0;
    private Double moveWeight = 0.0;
    private String otherPossessions = "";

    //Spells
    private SheetSpells spells;

    //Description
    private String alignment = "";
    private String gender = "";
    private String eyes = "";
    private String size = "";
    private String faith = "";
    private String hair = "";
    private String skin = "";
    private int age = 0;
    private String height = "";
    private String weight = "";
    private String traits = "";
    private String ideals = "";
    private String bonds = "";
    private String flaws = "";
    private String appearance = "";

    //Notes
    private String organizations = "";
    private String allies = "";
    private String enemies = "";
    private String backstory = "";
    private String other = "";

    //Config
    private int encumbranceRule = 0;
    private boolean ignoreCoinWeight;

    private ArrayList<DiceRollRegistry> diceRollRegistry;

    public static final String TABLE = "SHEET";
    public static final String ID = "SHEET_ID";
    public static final String NAME = "NAME";
    public static final String RACEID = "RACE_ID";
    public static final String CLASSID = "CLASS_ID";
    public static final String BACKGROUNDID = "BACKGROUND_ID";
    public static final String LIFESTYLEID = "LIFESTYLE_ID";

    public static final String MAXHP = "MAXHP";
    public static final String HP = "HP";
    public static final String EXP = "EXP";
    public static final String SPEED = "SPEED";
    public static final String PROFICIENCYMOD = "PROFICIENCYMOD";
    public static final String INITIATIVE = "INITIATIVE";
    public static final String ARMORMOD = "ARMORMOD";
    public static final String INSPIRATION = "INSPIRATION";

    public static final String STRENGHT = "STRENGHT";
    public static final String DEXTERITY = "DEXTERITY";
    public static final String CONSTITUTION = "CONSTITUTION";
    public static final String INTELLIGENCE = "INTELLIGENCE";
    public static final String WISDOM = "WISDOM";
    public static final String CHARISMA = "CHARISMA";

    public static final String ARMORPROF = "ARMORPROF";
    public static final String WEAPONPROF = "WEAPONPROF";
    public static final String TOOLPROF = "TOOLPROF";
    public static final String LANGUAGES = "LANGUAGES";

    //Table Skill
    public static final String TABLESKILL = "SKILL";
    public static final String SKILLID = "SKILL_ID";
    public static final String SKILLNAME = "NAME";
    public static final String SKILLVALUE = "VALUE";
    public static final String SKILLPROFICIENCY = "PROFICIENCY";
    //////////////////////////

    public static final String RESISTANCES = "RESISTANCES";
    public static final String IMMUNITIES = "IMMUNITIES";
    public static final String VULNERABILITIES = "VULNERABILITIES";

    public static final String EXHAUSTION = "EXHAUSTION";
    public static final String CONDITIONS = "CONDITIONS";

    public static final String COPPER = "COPPER";
    public static final String SILVER = "SILVER";
    public static final String ELECTRUM = "ELECTRUM";
    public static final String GOLD = "GOLD";
    public static final String PLATINUM = "PLATINUM";
    public static final String CARRYINGCAPACITY = "CARRYINGCAPACITY";
    public static final String WEIGHTCARRIED = "WEIGHTCARRIED";
    public static final String MOVEWEIGHT = "MOVEWEIGHT";
    public static final String OTHERPOSSESSIONS = "OTHERPOSSESSIONS";

    public static final String ALIGNMENT = "ALIGNMENT";
    public static final String GENDER = "GENDER";
    public static final String EYES = "EYES";
    public static final String SIZE = "SIZE";
    public static final String FAITH = "FAITH";
    public static final String HAIR = "HAIR";
    public static final String SKIN = "SKIN";
    public static final String AGE = "AGE";
    public static final String HEIGHT = "HEIGHT";
    public static final String WEIGHT = "WEIGHT";
    public static final String TRAITS = "TRAITS";
    public static final String IDEALS = "IDEALS";
    public static final String BONDS = "BONDS";
    public static final String FLAWS = "FLAWS";
    public static final String APPEARANCE = "APPEARANCE";

    public static final String ORGANIZATIONS = "ORGANIZATIONS";
    public static final String ALLIES = "ALLIES";
    public static final String ENEMIES = "ENEMIES";
    public static final String BACKSTORY = "BACKSTORY";
    public static final String OTHER = "OTHER";

    public static final String ENCUMBRANCERULE = "ENCUMBRANCERULE";
    public static final String IGNORECOINWEIGHT = "IGNORECOINWEIGHT";

    public Sheet(){
        attributes = new HashMap<>();
        skills = new HashMap<>();
        skillsProficiency = new HashMap<>();
        conditions = new HashMap<>();
    }

    public void buildStats(){
        //TODO buildStats
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public CharClass getCharClass() {
        return charClass;
    }

    public void setCharClass(CharClass charClass) {
        this.charClass = charClass;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public int getLevel(){
        if (exp < 300) return 1;
        else if (exp < 900) return 2;
        else if (exp < 2700) return 3;
        else if (exp < 6500) return 4;
        else if (exp < 14000) return 5;
        else if (exp < 23000) return 6;
        else if (exp < 34000) return 7;
        else if (exp < 48000) return 8;
        else if (exp < 64000) return 9;
        else if (exp < 85000) return 10;
        else if (exp < 100000) return 11;
        else if (exp < 120000) return 12;
        else if (exp < 140000) return 13;
        else if (exp < 165000) return 14;
        else if (exp < 195000) return 15;
        else if (exp < 225000) return 16;
        else if (exp < 265000) return 17;
        else if (exp < 305000) return 18;
        else if (exp < 355000) return 19;
        else return 20;
    }

    public int getProfBonus(){
        if (exp < 6500) return 2;
        else if (exp < 48000) return 3;
        else if (exp < 120000) return 4;
        else if (exp < 225000) return 5;
        else return 6;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public boolean isInspiration() {
        return inspiration;
    }

    public void setInspiration(boolean inspiration) {
        this.inspiration = inspiration;
    }

    public int getAttribute(String type, String attributeType){
        HashMap<String, Integer> attributeList = attributes.get(type);
        if (attributeList != null && attributeList.get(attributeType) != null)
            return attributeList.get(attributeType);
        else
            return 0;
    }

    public void setAttribute(String type, String attributeType, int value){
        HashMap<String, Integer> attributeList = attributes.get(type);
        if (attributeList != null){
            attributeList.put(attributeType, value);
            attributes.put(type, attributeList);
        } else {
            attributeList = new HashMap<>();
            attributeList.put(attributeType, value);
            attributes.put(type, attributeList);
        }
    }

    public int getPassivePerception() {
        return passivePerception;
    }

    public void setPassivePerception(int passivePerception) {
        this.passivePerception = passivePerception;
    }

    public int getPassiveInvestigation() {
        return passiveInvestigation;
    }

    public void setPassiveInvestigation(int passiveInvestigation) {
        this.passiveInvestigation = passiveInvestigation;
    }

    public int getPassiveInsight() {
        return passiveInsight;
    }

    public void setPassiveInsight(int passiveInsight) {
        this.passiveInsight = passiveInsight;
    }

    public String getArmorProficiency() {
        return armorProficiency;
    }

    public void setArmorProficiency(String armorProficiency) {
        this.armorProficiency = armorProficiency;
    }

    public String getWeaponProficiency() {
        return weaponProficiency;
    }

    public void setWeaponProficiency(String weaponProficiency) {
        this.weaponProficiency = weaponProficiency;
    }

    public String getToolProficiency() {
        return toolProficiency;
    }

    public void setToolProficiency(String toolProficiency) {
        this.toolProficiency = toolProficiency;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public int getSkillValue(String skill){
        if (skills != null && skills.get(skill) != null)
            return skills.get(skill);
        else
            return 0;
    }

    public void setSkill(String skill, int value){
        skills.put(skill, value);
    }

    public boolean getSkillProficiency(String skill){
        if (skillsProficiency != null && skillsProficiency.get(skill) != null)
            return skillsProficiency.get(skill);
        else
            return false;
    }

    public void setSkillProficiency(String skill, boolean value){
        skillsProficiency.put(skill, value);
    }

    public String getResistances() {
        return resistances;
    }

    public void setResistances(String resistances) {
        this.resistances = resistances;
    }

    public String getImmunities() {
        return immunities;
    }

    public void setImmunities(String immunities) {
        this.immunities = immunities;
    }

    public String getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(String vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    public int getExhaustion() {
        return exhaustion;
    }

    public void setExhaustion(int exhaustion) {
        this.exhaustion = exhaustion;
    }

    public boolean getCondition(String condition){
        if (conditions != null)
            return conditions.get(condition);
        else
            return false;
    }

    public void setCondition(String condition, boolean value){
        conditions.put(condition, value);
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Equipment> equipment) {
        this.equipment = equipment;
    }

    public void addEquipment(Item item, int quantity){
        Equipment equip = new Equipment();
        if (item instanceof Weapon)
            equip.setWeapon((Weapon) item);
        else if (item instanceof Armor)
            equip.setArmor((Armor) item);
        else
            equip.setItem(item);
        equip.setQuantity(quantity);
        equipment.add(equip);
    }

    public LifeStyle getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(LifeStyle lifestyle) {
        this.lifestyle = lifestyle;
    }

    public int getCopper() {
        return copper;
    }

    public void setCopper(int copper) {
        this.copper = copper;
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public int getElectrum() {
        return electrum;
    }

    public void setElectrum(int electrum) {
        this.electrum = electrum;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getPlatinum() {
        return platinum;
    }

    public void setPlatinum(int platinum) {
        this.platinum = platinum;
    }

    public Double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(Double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public Double getWeightCarried() {
        return weightCarried;
    }

    public void setWeightCarried(Double weightCarried) {
        this.weightCarried = weightCarried;
    }

    public Double getMoveWeight() {
        return moveWeight;
    }

    public void setMoveWeight(Double moveWeight) {
        this.moveWeight = moveWeight;
    }

    public String getOtherPossessions() {
        return otherPossessions;
    }

    public void setOtherPossessions(String otherPossessions) {
        this.otherPossessions = otherPossessions;
    }

    public SheetSpells getSpells() {
        return spells;
    }

    public void setSpells(SheetSpells spells) {
        this.spells = spells;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFaith() {
        return faith;
    }

    public void setFaith(String faith) {
        this.faith = faith;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTraits() {
        return traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

    public String getIdeals() {
        return ideals;
    }

    public void setIdeals(String ideals) {
        this.ideals = ideals;
    }

    public String getBonds() {
        return bonds;
    }

    public void setBonds(String bonds) {
        this.bonds = bonds;
    }

    public String getFlaws() {
        return flaws;
    }

    public void setFlaws(String flaws) {
        this.flaws = flaws;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getOrganizations() {
        return organizations;
    }

    public void setOrganizations(String organizations) {
        this.organizations = organizations;
    }

    public String getAllies() {
        return allies;
    }

    public void setAllies(String allies) {
        this.allies = allies;
    }

    public String getEnemies() {
        return enemies;
    }

    public void setEnemies(String enemies) {
        this.enemies = enemies;
    }

    public String getBackstory() {
        return backstory;
    }

    public void setBackstory(String backstory) {
        this.backstory = backstory;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int getEncumbranceRule() {
        return encumbranceRule;
    }

    public void setEncumbranceRule(int encumbranceRule) {
        this.encumbranceRule = encumbranceRule;
    }

    public boolean getIgnoreCoinWeight() {
        return ignoreCoinWeight;
    }

    public void setIgnoreCoinWeight(boolean ignoreCoinWeight) {
        this.ignoreCoinWeight = ignoreCoinWeight;
    }

    public ArrayList<DiceRollRegistry> getDiceRollRegistry() {
        return diceRollRegistry;
    }

    public void setDiceRollRegistry(ArrayList<DiceRollRegistry> diceRollRegistry) {
        this.diceRollRegistry = diceRollRegistry;
    }

    public void addDiceRollRegistry(DiceRollRegistry diceRoll){
        this.diceRollRegistry.add(diceRoll);
    }

    public static String getTableCreate(){
        return "CREATE TABLE "+TABLE+"(" +
                ID + " integer primary key autoincrement, " +
                NAME + " text, " +
                RACEID + " integer, " +
                CLASSID + " integer, " +
                BACKGROUNDID + " integer, " +
                LIFESTYLEID + " integer, " +

                MAXHP + " integer, " +
                HP + " integer, " +
                EXP + " integer, " +
                SPEED + " integer, " +

                PROFICIENCYMOD + " integer, " +
                INITIATIVE + " integer, " +
                ARMORMOD + " integer, " +
                INSPIRATION + " numeric, " +

                STRENGHT + " integer, " +
                DEXTERITY + " integer, " +
                CONSTITUTION + " integer, " +
                INTELLIGENCE + " integer, " +
                WISDOM + " integer, " +
                CHARISMA + " integer, " +

                ARMORPROF + " text, " +
                WEAPONPROF + " text, " +
                TOOLPROF + " text, " +
                LANGUAGES + " text, " +

                RESISTANCES + " text, " +
                IMMUNITIES + " text, " +
                VULNERABILITIES + " text, " +
                EXHAUSTION + " integer, " +
                CONDITIONS + " text, " +

                COPPER + " integer, " +
                SILVER + " integer, " +
                ELECTRUM + " integer, " +
                GOLD + " integer, " +
                PLATINUM + " integer, " +

                CARRYINGCAPACITY + " real, " +
                WEIGHTCARRIED + " real, " +
                MOVEWEIGHT + " real, " +

                OTHERPOSSESSIONS + " text, " +

                ALIGNMENT + " text, " +
                GENDER + " text, " +
                EYES + " text, " +
                SIZE + " text, " +
                FAITH + " text, " +
                HAIR + " text, " +
                SKIN + " text, " +
                AGE + " integer, " +
                HEIGHT + " text, " +
                WEIGHT + " text, " +
                TRAITS + " text, " +
                IDEALS + " text, " +
                BONDS + " text, " +
                FLAWS + " text, " +
                APPEARANCE + " text, " +

                ORGANIZATIONS + " text, " +
                ALLIES + " text, " +
                ENEMIES + " text, " +
                BACKSTORY + " text, " +
                OTHER + " text, " +
                ENCUMBRANCERULE + " integer, "+
                IGNORECOINWEIGHT + " numeric, " +
                    "FOREIGN KEY ("+CLASSID+") REFERENCES "+CharClass.TABLE+"("+CharClass.ID+"), " +
                    "FOREIGN KEY ("+RACEID+") REFERENCES "+Race.TABLE+"("+Race.ID+"), " +
                    "FOREIGN KEY ("+BACKGROUNDID+") REFERENCES "+Background.TABLE+"("+Background.ID+"), " +
                    "FOREIGN KEY ("+LIFESTYLEID+") REFERENCES "+LifeStyle.TABLE+"("+LifeStyle.ID+") " +
                ")";
    }

    public static String getTableDrop(){
        return "DROP TABLE IF EXISTS " + TABLE;
    }

    public static String getTableSkillCreate(){
        return "CREATE TABLE "+TABLESKILL+"(" +
                SKILLID + " integer primary key autoincrement, " +
                ID + " integer, " +
                SKILLNAME + " text, " +
                SKILLVALUE + " integer, " +
                SKILLPROFICIENCY + " numeric, " +
                    "FOREIGN KEY ("+ID+") REFERENCES "+TABLE+"("+ID+") " +
                ")";
    }

    public static String getTableSkillDrop(){
        return "DROP TABLE IF EXISTS " + TABLESKILL;
    }

    //TODO temporaryHitpoits
}
