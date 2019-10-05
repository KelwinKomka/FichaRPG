package com.ES3.ficharpg.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ES3.ficharpg.model.Action;
import com.ES3.ficharpg.model.Armor;
import com.ES3.ficharpg.model.DiceRollRegistry;
import com.ES3.ficharpg.model.Equipment;
import com.ES3.ficharpg.model.Item;
import com.ES3.ficharpg.model.Sheet;
import com.ES3.ficharpg.Program;
import com.ES3.ficharpg.R;
import com.ES3.ficharpg.model.SheetSpells;
import com.ES3.ficharpg.model.Spell;
import com.ES3.ficharpg.model.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Character info
    private TextView txtName;
    private TextView txtHP;

    //Stats
    private TextView txtContStr;
    private TextView txtContStrMod;
    private TextView txtContStrSave;

    private TextView txtContDex;
    private TextView txtContDexMod;
    private TextView txtContDexSave;

    private TextView txtContCon;
    private TextView txtContConMod;
    private TextView txtContConSave;

    private TextView txtContInt;
    private TextView txtContIntMod;
    private TextView txtContIntSave;

    private TextView txtContWis;
    private TextView txtContWisMod;
    private TextView txtContWisSave;

    private TextView txtContCha;
    private TextView txtContChaMod;
    private TextView txtContChaSave;

    //Info
    private TextView txtContExp;
    private TextView txtContProf;
    private TextView txtContSpeed;
    private TextView txtContInitiative;
    private TextView txtContArmorClass;
    private TextView txtContPassivePerception;
    private TextView txtContPassiveInvestigation;
    private TextView txtContPassiveInsight;
    private CheckBox chbInspiration;

    //Skills
    private TextView txtContAcrobaticsMod;
    private CheckBox chbProfAcrobatics;

    private TextView txtContAracanaMod;
    private CheckBox chbProfArcana;

    private TextView txtContAthleticsMod;
    private CheckBox chbProfAthletics;

    private TextView txtContPerformanceMod;
    private CheckBox chbProfPerformance;

    private TextView txtContDeceptionMod;
    private CheckBox chbProfDeception;

    private TextView txtContStealthsMod;
    private CheckBox chbProfStealth;

    private TextView txtContHistoryMod;
    private CheckBox chbProfHistory;

    private TextView txtContInitmidationMod;
    private CheckBox chbProfIntimidation;

    private TextView txtContInsightMod;
    private CheckBox chbProfInsight;

    private TextView txtContInvestigationMod;
    private CheckBox chbProfInvestigation;

    private TextView txtContAnimalHandlingMod;
    private CheckBox chbProfAnimalHandling;

    private TextView txtContMedicineMod;
    private CheckBox chbProfMedicine;

    private TextView txtContNatureMod;
    private CheckBox chbProfNature;

    private TextView txtContPerceptionMod;
    private CheckBox chbProfPerception;

    private TextView txtContPersuasionMod;
    private CheckBox chbProfPersuasion;

    private TextView txtContSleightOfHandMod;
    private CheckBox chbProSleightOfHand;

    private TextView txtContReligionMod;
    private CheckBox chbProfReligion;

    private TextView txtContSurvivalMod;
    private CheckBox chbProfSurvival;

    //Action
    private TableLayout actionTable;

    //Equipment
    private TableLayout equipmentTable;
    private EditText edtCopper;
    private EditText edtSilver;
    private EditText edtGold;
    private EditText edtElectrum;
    private EditText edtPlatinum;

    //Spell
    private TableLayout spellTable;

    //Feature
    private TableLayout featureTable;

    //Description
    private TextView txtContAlignment;
    private TextView txtContGender;
    private TextView txtContEyes;
    private TextView txtContSize;
    private TextView txtContHeight;
    private TextView txtContAge;
    private TextView txtContFaith;
    private EditText txtContTraits;

    //Note
    private EditText txtContNotes;

    //Dice
    private TextView txtD4;
    private TextView txtD6;
    private TextView txtD8;
    private TextView txtD10;
    private TextView txtD12;
    private TextView txtD20;
    private TextView txtRoll;
    private EditText edtMod;
    private Button btnClear;
    private Button btnRoll;
    private HashMap<String, Integer> dice;

    private TabHost tabHost;

    private Sheet sheet;

    static final int CREATE_ITEM_REQUEST = 1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sheet = (Sheet) getIntent().getSerializableExtra("Sheet");

        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("Info");
        spec.setContent(R.id.infoTab);
        spec.setIndicator(getString(R.string.info));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Skills");
        spec.setContent(R.id.skillTab);
        spec.setIndicator(getString(R.string.skills));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Actions");
        spec.setContent(R.id.actionTab);
        spec.setIndicator(getString(R.string.actions));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Equipment");
        spec.setContent(R.id.equipmentTab);
        spec.setIndicator(getString(R.string.equipment));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Spells");
        spec.setContent(R.id.spellTab);
        spec.setIndicator(getString(R.string.spells));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Feature");
        spec.setContent(R.id.featureTab);
        spec.setIndicator(getString(R.string.features));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Descritpion");
        spec.setContent(R.id.descriptionTab);
        spec.setIndicator(getString(R.string.descriptions));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Note");
        spec.setContent(R.id.noteTab);
        spec.setIndicator(getString(R.string.notes));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Dice");
        spec.setContent(R.id.diceTab);
        spec.setIndicator(getString(R.string.dice));
        tabHost.addTab(spec);

        txtName = findViewById(R.id.txtName);
        txtHP = findViewById(R.id.txtHP);

        registerForContextMenu(txtName);

        txtHP.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final AlertDialog.Builder popDialog = new AlertDialog.Builder(MainActivity.this);
                final SeekBar seek = new SeekBar(MainActivity.this);
                seek.setMax(sheet.getMaxHitPoints());
                seek.setKeyProgressIncrement(1);
                seek.setProgress(sheet.getHitPoints());

                popDialog.setTitle("Pontos de vida");
                popDialog.setView(seek);

                seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                        sheet.setHitPoints(progress);
                        changeLifeBar();
                    }

                    public void onStartTrackingTouch(SeekBar arg0) {
                        //do something
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                        //do something
                    }
                });


               popDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                   }
               });
               popDialog.create();
               popDialog.show();
               return false;
            }
        });

        //Stats
        txtContStr = findViewById(R.id.txtContStr);
        txtContStrMod = findViewById(R.id.txtContStrMod);
        txtContStrSave = findViewById(R.id.txtContStrSave);

        txtContStrMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContStrMod.getText().toString()), "Força");
                }
                return true;
            }
        });

        txtContStrSave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContStrSave.getText().toString()), "Resistência de força");
                }
                return true;
            }
        });

        txtContDex  = findViewById(R.id.txtContDex);
        txtContDexMod = findViewById(R.id.txtContDexMod);
        txtContDexSave = findViewById(R.id.txtContDexSave);

        txtContDexMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContDexMod.getText().toString()), "Destreza");
                }
                return true;
            }
        });

        txtContDexSave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContDexSave.getText().toString()), "Resistência de destreza");
                }
                return true;
            }
        });

        txtContCon = findViewById(R.id.txtContCon);
        txtContConMod = findViewById(R.id.txtContConMod);
        txtContConSave = findViewById(R.id.txtContConSave);

        txtContConMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContConMod.getText().toString()), "Constituição");
                }
                return true;
            }
        });

        txtContConSave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContConSave.getText().toString()), "Resistência de constituição");
                }
                return true;
            }
        });

        txtContInt = findViewById(R.id.txtContInt);
        txtContIntMod = findViewById(R.id.txtContIntMod);
        txtContIntSave = findViewById(R.id.txtContIntSave);

        txtContIntMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContIntMod.getText().toString()), "Inteligência");
                }
                return true;
            }
        });

        txtContIntSave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContIntSave.getText().toString()), "Resistência de inteligência");
                }
                return true;
            }
        });

        txtContWis = findViewById(R.id.txtContWis);
        txtContWisMod = findViewById(R.id.txtContWisMod);
        txtContWisSave = findViewById(R.id.txtContWisSave);

        txtContWisMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContWisMod.getText().toString()), "Sabedoria");
                }
                return true;
            }
        });

        txtContWisSave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContWisSave.getText().toString()), "Resistência de sabedoria");
                }
                return true;
            }
        });

        txtContCha = findViewById(R.id.txtContCha);
        txtContChaMod = findViewById(R.id.txtContChaMod);
        txtContChaSave = findViewById(R.id.txtContChaSave);

        txtContChaMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContChaMod.getText().toString()), "Carisma");
                }
                return true;
            }
        });

        txtContChaSave.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContChaSave.getText().toString()), "Resistência de carisma");
                }
                return true;
            }
        });

        //Info
        txtContExp = findViewById(R.id.txtContExp);
        txtContProf = findViewById(R.id.txtContProf);
        txtContSpeed = findViewById(R.id.txtContSpeed);
        txtContInitiative = findViewById(R.id.txtContInitiative);

        txtContInitiative.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContInitiative.getText().toString().replace("+", "")), "Iniciativa");
                }
                return true;
            }
        });

        txtContArmorClass = findViewById(R.id.txtContArmorClass);
        txtContPassivePerception = findViewById(R.id.txtContPassivePerception);
        txtContPassiveInvestigation = findViewById(R.id.txtContPassiveInvestigation);
        txtContPassiveInsight = findViewById(R.id.txtContPassiveInsight);
        chbInspiration = findViewById(R.id.chbInspiration);

        //Skills
        txtContAcrobaticsMod = findViewById(R.id.txtContAcrobaticsMod);
        chbProfAcrobatics = findViewById(R.id.chbProfAcrobatics);

        txtContAcrobaticsMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContAcrobaticsMod.getText().toString()), getString(R.string.ACROBATICS));
                }
                return true;
            }
        });

        chbProfAcrobatics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContAcrobaticsMod, Program.ACROBATICS, isChecked);
            }
        });

        txtContAracanaMod = findViewById(R.id.txtContArcanaMod);
        chbProfArcana = findViewById(R.id.chbProfArcana);

        txtContAracanaMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContAracanaMod.getText().toString()), getString(R.string.ARCANA));
                }
                return true;
            }
        });

        chbProfArcana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContAracanaMod, Program.ARCANA, isChecked);
            }
        });

        txtContAthleticsMod = findViewById(R.id.txtContAthleticsMod);
        chbProfAthletics = findViewById(R.id.chbProfAthletics);

        txtContAthleticsMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContAthleticsMod.getText().toString()), getString(R.string.ATHLETICS));
                }
                return true;
            }
        });

        chbProfAthletics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContAthleticsMod, Program.ATHLETICS, isChecked);
            }
        });

        txtContPerformanceMod = findViewById(R.id.txtContPerformanceMod);
        chbProfPerformance = findViewById(R.id.chbProfPerformance);

        txtContPerformanceMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContPerformanceMod.getText().toString()), getString(R.string.PERFORMANCE));
                }
                return true;
            }
        });

        chbProfPerformance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContPerformanceMod, Program.PERFORMANCE, isChecked);
            }
        });

        txtContDeceptionMod = findViewById(R.id.txtContDeceptionMod);
        chbProfDeception = findViewById(R.id.chbProfDeception);

        txtContDeceptionMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContDeceptionMod.getText().toString()), getString(R.string.DECEPTION));
                }
                return true;
            }
        });

        chbProfDeception.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContDeceptionMod, Program.DECEPTION, isChecked);
            }
        });

        txtContStealthsMod = findViewById(R.id.txtContStealthMod);
        chbProfStealth = findViewById(R.id.chbProfStealth);

        txtContStealthsMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContStealthsMod.getText().toString()), getString(R.string.STEALTH));
                }
                return true;
            }
        });

        chbProfStealth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContStealthsMod, Program.STEALTH, isChecked);
            }
        });

        txtContHistoryMod = findViewById(R.id.txtContHistoryMod);
        chbProfHistory = findViewById(R.id.chbProfHistory);

        txtContHistoryMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContHistoryMod.getText().toString()), getString(R.string.HISTORY));
                }
                return true;
            }
        });

        chbProfHistory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContHistoryMod, Program.HISTORY, isChecked);
            }
        });

        txtContInitmidationMod = findViewById(R.id.txtContIntimidationMod);
        chbProfIntimidation = findViewById(R.id.chbProfIntimidation);

        txtContInitmidationMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContInitmidationMod.getText().toString()), getString(R.string.INTIMIDATION));
                }
                return true;
            }
        });

        chbProfIntimidation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContInitmidationMod, Program.INTIMIDATION, isChecked);
            }
        });

        txtContInsightMod = findViewById(R.id.txtContInsightMod);
        chbProfInsight = findViewById(R.id.chbProfInsight);

        txtContInsightMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContInsightMod.getText().toString()), getString(R.string.INSIGHT));
                }
                return true;
            }
        });

        chbProfInsight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContInsightMod, Program.INSIGHT, isChecked);
            }
        });

        txtContInvestigationMod = findViewById(R.id.txtContInvestigationMod);
        chbProfInvestigation = findViewById(R.id.chbProfInvestigation);

        txtContInvestigationMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContInvestigationMod.getText().toString()), getString(R.string.INVESTIGATION));
                }
                return true;
            }
        });

        chbProfInvestigation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContInvestigationMod, Program.INVESTIGATION, isChecked);
            }
        });

        txtContAnimalHandlingMod = findViewById(R.id.txtContAnimalHandlingMod);
        chbProfAnimalHandling = findViewById(R.id.chbProfAnimalHandling);

        txtContAnimalHandlingMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContAnimalHandlingMod.getText().toString()), getString(R.string.ANIMALHANDLING));
                }
                return true;
            }
        });

        chbProfAnimalHandling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContAnimalHandlingMod, Program.ANIMALHANDLING, isChecked);
            }
        });

        txtContMedicineMod = findViewById(R.id.txtContMedicineMod);
        chbProfMedicine = findViewById(R.id.chbProfMedicine);

        txtContMedicineMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContMedicineMod.getText().toString()), getString(R.string.MEDICINE));
                }
                return true;
            }
        });

        chbProfMedicine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContMedicineMod, Program.MEDICINE, isChecked);
            }
        });

        txtContNatureMod = findViewById(R.id.txtContNatureMod);
        chbProfNature = findViewById(R.id.chbProfNature);

        txtContNatureMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContNatureMod.getText().toString()), getString(R.string.NATURE));
                }
                return true;
            }
        });

        chbProfNature.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContNatureMod, Program.NATURE, isChecked);
            }
        });

        txtContPerceptionMod = findViewById(R.id.txtContPerceptionMod);
        chbProfPerception = findViewById(R.id.chbProfPerception);

        txtContPerceptionMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContPerceptionMod.getText().toString()), getString(R.string.PERCEPTION));
                }
                return true;
            }
        });

        chbProfPerception.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContPerceptionMod, Program.PERCEPTION, isChecked);
            }
        });

        txtContPersuasionMod = findViewById(R.id.txtContPersuasionMod);
        chbProfPersuasion = findViewById(R.id.chbProfPersuasion);

        txtContPersuasionMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContPersuasionMod.getText().toString()), getString(R.string.PERSUASION));
                }
                return true;
            }
        });

        chbProfPersuasion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContPersuasionMod, Program.PERSUASION, isChecked);
            }
        });

        txtContSleightOfHandMod = findViewById(R.id.txtContSleightOfHandMod);
        chbProSleightOfHand = findViewById(R.id.chbProfSleightOfHand);

        txtContSleightOfHandMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContSleightOfHandMod.getText().toString()), getString(R.string.SLEIGHTOFHAND));
                }
                return true;
            }
        });

        chbProSleightOfHand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContSleightOfHandMod, Program.SLEIGHTOFHAND, isChecked);
            }
        });

        txtContReligionMod = findViewById(R.id.txtContReligionMod);
        chbProfReligion = findViewById(R.id.chbProfReligion);

        txtContReligionMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContReligionMod.getText().toString()), getString(R.string.RELIGION));
                }
                return true;
            }
        });

        chbProfReligion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContReligionMod, Program.RELIGION, isChecked);
            }
        });

        txtContSurvivalMod = findViewById(R.id.txtContSurvivalMod);
        chbProfSurvival = findViewById(R.id.chbProfSurvival);

        txtContSurvivalMod.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touchEvent(event, 20, 1, Integer.parseInt(txtContSurvivalMod.getText().toString()), getString(R.string.SURVIVAL));
                }
                return true;
            }
        });

        chbProfSurvival.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSkillMod(txtContSurvivalMod, Program.SURVIVAL, isChecked);
            }
        });

        //Action
        actionTable = findViewById(R.id.actionTable);

        //Equipment
        equipmentTable = findViewById(R.id.equipmentTable);
        edtCopper = findViewById(R.id.edtCopper);
        edtSilver = findViewById(R.id.edtSilver);
        edtGold = findViewById(R.id.edtGold);
        edtElectrum = findViewById(R.id.edtElectrum);
        edtPlatinum = findViewById(R.id.edtPlatinum);

        //Spell
        spellTable = findViewById(R.id.spellTable);

        //Feature
        featureTable = findViewById(R.id.featureTable);

        //Description
        txtContAlignment = findViewById(R.id.txtContAlignment);
        txtContGender = findViewById(R.id.edtGender);
        txtContEyes = findViewById(R.id.edtEyes);
        txtContSize = findViewById(R.id.edtSize);
        txtContHeight = findViewById(R.id.edtHeight);
        txtContAge = findViewById(R.id.edtAge);
        txtContFaith = findViewById(R.id.txtContFaith);
        txtContTraits = findViewById(R.id.txtContTraits);

        txtContTraits.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                sheet.setTraits(s.toString());
            }
        });

        //Note
        txtContNotes = findViewById(R.id.txtContNotes);

        txtD4 = findViewById(R.id.txtD4);
        txtD6 = findViewById(R.id.txtD6);
        txtD8 = findViewById(R.id.txtD8);
        txtD10 = findViewById(R.id.txtD10);
        txtD12 = findViewById(R.id.txtD12);
        txtD20 = findViewById(R.id.txtD20);
        txtRoll = findViewById(R.id.txtRoll);
        edtMod = findViewById(R.id.edtMod);
        btnClear = findViewById(R.id.btnClear);
        btnRoll = findViewById(R.id.btnRoll);

        txtD4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                addDie("d4");
                return false;
            }
        });

        txtD6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                addDie("d6");
                return false;
            }
        });

        txtD8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                addDie("d8");
                return false;
            }
        });

        txtD10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                addDie("d10");
                return false;
            }
        });

        txtD12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                addDie("d12");
                return false;
            }
        });

        txtD20.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                addDie("d20");
                return false;
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDice();
            }
        });

        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        if (sheet == null)
            temporarySheet();
        fillOut();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == txtName.getId())
            getMenuInflater().inflate(R.menu.menu_sheet, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuItemRoll:
                Intent intent = new Intent(this, RollRegistryActivity.class);
                intent.putExtra("diceRegistry", sheet.getDiceRollRegistry());
                startActivity(intent);
                return true;
            case R.id.menuItemModify:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Confirmação")
                        .setMessage("Deseja modificar a ficha?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                Intent intent = new Intent(MainActivity.this, SheetCreationActivity.class);
                                intent.putExtra("Sheet", sheet);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }}).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void fillOut(){
        String name = sheet.getName() + " (nv " + sheet.getLevel() + ")";
        txtName.setText(name);
        changeLifeBar();
        changeExpBar();

        //Stats
        txtContStr.setText(String.valueOf(sheet.getAttribute(Program.SCORE, Program.STRENGTH)));
        txtContStrMod.setText(String.valueOf(sheet.getAttribute(Program.MODIFIER, Program.STRENGTH)));
        txtContStrSave.setText(String.valueOf(sheet.getAttribute(Program.SAVING, Program.STRENGTH)));

        txtContDex.setText(String.valueOf(sheet.getAttribute(Program.SCORE, Program.DEXTERITY)));
        txtContDexMod.setText(String.valueOf(sheet.getAttribute(Program.MODIFIER, Program.DEXTERITY)));
        txtContDexSave.setText(String.valueOf(sheet.getAttribute(Program.SAVING, Program.DEXTERITY)));

        txtContCon.setText(String.valueOf(sheet.getAttribute(Program.SCORE, Program.CONSTITUTION)));
        txtContConMod.setText(String.valueOf(sheet.getAttribute(Program.MODIFIER, Program.CONSTITUTION)));
        txtContConSave.setText(String.valueOf(sheet.getAttribute(Program.SAVING, Program.CONSTITUTION)));

        txtContInt.setText(String.valueOf(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        txtContIntMod.setText(String.valueOf(sheet.getAttribute(Program.MODIFIER, Program.INTELLIGENCE)));
        txtContIntSave.setText(String.valueOf(sheet.getAttribute(Program.SAVING, Program.INTELLIGENCE)));

        txtContWis.setText(String.valueOf(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        txtContWisMod.setText(String.valueOf(sheet.getAttribute(Program.MODIFIER, Program.WISDOM)));
        txtContWisSave.setText(String.valueOf(sheet.getAttribute(Program.SAVING, Program.WISDOM)));

        txtContCha.setText(String.valueOf(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));
        txtContChaMod.setText(String.valueOf(sheet.getAttribute(Program.MODIFIER, Program.CHARISMA)));
        txtContChaSave.setText(String.valueOf(sheet.getAttribute(Program.SAVING, Program.CHARISMA)));

        //Info
        String info = "+" + String.valueOf(sheet.getProficiency());
        txtContProf.setText(info);
        info = String.valueOf(sheet.getSpeed()) + "ft";
        txtContSpeed.setText(info);
        info = "+" + String.valueOf(sheet.getInitiative());
        txtContInitiative.setText(info);
        info = String.valueOf(sheet.getArmor()) + " AC";
        txtContArmorClass.setText(info);
        txtContPassivePerception.setText(String.valueOf(sheet.getPassivePerception()));
        txtContPassiveInvestigation.setText(String.valueOf(sheet.getPassiveInvestigation()));
        txtContPassiveInsight.setText(String.valueOf( sheet.getPassiveInsight()));
        chbInspiration.setChecked(false);

        //Skills
        txtContAcrobaticsMod.setText(String.valueOf(sheet.getSkillValue(Program.ACROBATICS)));
        chbProfAcrobatics.setChecked(sheet.getSkillProficiency(Program.ACROBATICS));

        txtContAracanaMod.setText(String.valueOf(sheet.getSkillValue(Program.ARCANA)));
        chbProfArcana.setChecked(sheet.getSkillProficiency(Program.ARCANA));

        txtContAthleticsMod.setText(String.valueOf(sheet.getSkillValue(Program.ATHLETICS)));
        chbProfAthletics.setChecked(sheet.getSkillProficiency(Program.ATHLETICS));

        txtContPerformanceMod.setText(String.valueOf(sheet.getSkillValue(Program.PERFORMANCE)));
        chbProfPerformance.setChecked(sheet.getSkillProficiency(Program.PERFORMANCE));

        txtContDeceptionMod.setText(String.valueOf(sheet.getSkillValue(Program.DECEPTION)));
        chbProfDeception.setChecked(sheet.getSkillProficiency(Program.DECEPTION));

        txtContStealthsMod.setText(String.valueOf(sheet.getSkillValue(Program.STEALTH)));
        chbProfStealth.setChecked(sheet.getSkillProficiency(Program.STEALTH));

        txtContHistoryMod.setText(String.valueOf(sheet.getSkillValue(Program.HISTORY)));
        chbProfHistory.setChecked(sheet.getSkillProficiency(Program.HISTORY));

        txtContInitmidationMod.setText(String.valueOf(sheet.getSkillValue(Program.INTIMIDATION)));
        chbProfIntimidation.setChecked(sheet.getSkillProficiency(Program.INTIMIDATION));

        txtContInsightMod.setText(String.valueOf(sheet.getSkillValue(Program.INSIGHT)));
        chbProfInsight.setChecked(sheet.getSkillProficiency(Program.INSIGHT));

        txtContInvestigationMod.setText(String.valueOf(sheet.getSkillValue(Program.INVESTIGATION)));
        chbProfInvestigation.setChecked(sheet.getSkillProficiency(Program.INVESTIGATION));

        txtContAnimalHandlingMod.setText(String.valueOf(sheet.getSkillValue(Program.ANIMALHANDLING)));
        chbProfAnimalHandling.setChecked(sheet.getSkillProficiency(Program.ANIMALHANDLING));

        txtContMedicineMod.setText(String.valueOf(sheet.getSkillValue(Program.MEDICINE)));
        chbProfMedicine.setChecked(sheet.getSkillProficiency(Program.MEDICINE));

        txtContNatureMod.setText(String.valueOf(sheet.getSkillValue(Program.NATURE)));
        chbProfNature.setChecked(sheet.getSkillProficiency(Program.NATURE));

        txtContPerceptionMod.setText(String.valueOf(sheet.getSkillValue(Program.PERCEPTION)));
        chbProfPerception.setChecked(sheet.getSkillProficiency(Program.PERCEPTION));

        txtContPersuasionMod.setText(String.valueOf(sheet.getSkillValue(Program.PERSUASION)));
        chbProfPersuasion.setChecked(sheet.getSkillProficiency(Program.PERSUASION));

        txtContSleightOfHandMod.setText(String.valueOf(sheet.getSkillValue(Program.SLEIGHTOFHAND)));
        chbProSleightOfHand.setChecked(sheet.getSkillProficiency(Program.SLEIGHTOFHAND));

        txtContReligionMod.setText(String.valueOf(sheet.getSkillValue(Program.RELIGION)));
        chbProfReligion.setChecked(sheet.getSkillProficiency(Program.RELIGION));

        txtContSurvivalMod.setText(String.valueOf(sheet.getSkillValue(Program.SURVIVAL)));
        chbProfSurvival.setChecked(sheet.getSkillProficiency(Program.SURVIVAL));

        //Action
        refreshActions();

        //Equipment
        refreshEquipment();

        if (sheet.getCopper() > 0)
            edtCopper.setText(String.valueOf(sheet.getCopper()));
        if (sheet.getSilver() > 0)
            edtSilver.setText(String.valueOf(sheet.getSilver()));
        if (sheet.getGold() > 0)
            edtGold.setText(String.valueOf(sheet.getGold()));
        if (sheet.getElectrum() > 0)
            edtElectrum.setText(String.valueOf(sheet.getElectrum()));
        if (sheet.getPlatinum() > 0)
            edtPlatinum.setText(String.valueOf(sheet.getPlatinum()));

        //Spell
        refreshSpells();

        //Feature
        refreshFeatures();

        //Description
        txtContAlignment.setText(sheet.getAlignment());
        txtContGender.setText(sheet.getGender());
        txtContEyes.setText(sheet.getEyes());
        txtContSize.setText(sheet.getSize());
        txtContHeight.setText(sheet.getHeight());
        txtContAge.setText(String.valueOf(sheet.getAge()));
        txtContFaith.setText(sheet.getFaith());
        txtContTraits.setText(sheet.getTraits());

        //Note
        String notes = "Organizações: \n" + sheet.getOrganizations() +
                "\n\nAlidaos: \n" + sheet.getAllies() +
                "\n\nInimigos: \n" + sheet.getEnemies() +
                "\n\nAntepassado: \n" + sheet.getBackstory() +
                "\n\nOutros: \n" + sheet.getOther();
        txtContNotes.setText(notes);

        dice = new HashMap<>();
    }

    private void refreshFeatures() {
        featureTable.removeAllViews();

        int count = 0;

        TableRow tableRow = new TableRow(this);
        tableRow.setOrientation(TableRow.HORIZONTAL);
        tableRow.setBackground(getDrawable(R.drawable.layout_line_light));

        TextView txtDescription = new TextView(this);
        txtDescription.setText(R.string.description);
        txtDescription.setTextAppearance(R.style.tableHeader);
        txtDescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        TableRow.LayoutParams textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
        textParams.setMargins(2, 2, 2, 2);
        txtDescription.setLayoutParams(textParams);
        tableRow.addView(txtDescription);

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tableParams.setMargins(2, 2, 2, 2); // left, top, right, bottom
        tableRow.setLayoutParams(tableParams);
        featureTable.addView(tableRow);
        count++;

        if (sheet.getRace().getLanguages().size() > 0 && !"".equals(sheet.getRace().getLanguages().get(0))) {
            tableRow = new TableRow(this);
            tableRow.setOrientation(TableRow.VERTICAL);
            if (count % 2 == 0)
                tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
            else
                tableRow.setBackground(getDrawable(R.drawable.layout_line));

            txtDescription = new TextView(this);
            txtDescription.setText(R.string.languages);
            txtDescription.setTextAppearance(R.style.tableHeader);
            txtDescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
            textParams.setMargins(5, 2, 2, 2);
            txtDescription.setLayoutParams(textParams);
            tableRow.addView(txtDescription);

            tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableParams.setMargins(2, 2, 2, 0); // left, top, right, bottom
            tableRow.setLayoutParams(tableParams);
            featureTable.addView(tableRow);

            for (String language : sheet.getRace().getLanguages()) {
                tableRow = new TableRow(this);
                tableRow.setOrientation(TableRow.VERTICAL);
                if (count % 2 == 0)
                    tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
                else
                    tableRow.setBackground(getDrawable(R.drawable.layout_line));

                txtDescription = new TextView(this);
                txtDescription.setText(getString(getResources().getIdentifier(language, "string", getPackageName())));
                txtDescription.setTextAppearance(R.style.tableItem);
                txtDescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
                textParams.setMargins(50, 0, 0, 0);
                txtDescription.setLayoutParams(textParams);
                tableRow.addView(txtDescription);

                tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tableParams.setMargins(2, 0, 2, 0); // left, top, right, bottom
                tableRow.setLayoutParams(tableParams);
                featureTable.addView(tableRow);
            }
            count++;
        }

        if (sheet.getRace().getTraits().size() > 0 && !"".equals(sheet.getRace().getTraits().get(0))) {
            tableRow = new TableRow(this);
            tableRow.setOrientation(TableRow.VERTICAL);
            if (count % 2 == 0)
                tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
            else
                tableRow.setBackground(getDrawable(R.drawable.layout_line));

            txtDescription = new TextView(this);
            txtDescription.setText(R.string.traits);
            txtDescription.setTextAppearance(R.style.tableHeader);
            txtDescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
            textParams.setMargins(5, 2, 2, 2);
            txtDescription.setLayoutParams(textParams);
            tableRow.addView(txtDescription);

            tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableParams.setMargins(2, 0, 2, 0); // left, top, right, bottom
            tableRow.setLayoutParams(tableParams);
            featureTable.addView(tableRow);

            for (String traits : sheet.getRace().getTraits()) {
                tableRow = new TableRow(this);
                tableRow.setOrientation(TableRow.VERTICAL);
                if (count % 2 == 0)
                    tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
                else
                    tableRow.setBackground(getDrawable(R.drawable.layout_line));

                txtDescription = new TextView(this);
                txtDescription.setText(traits);
                txtDescription.setTextAppearance(R.style.tableItem);
                txtDescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
                textParams.setMargins(50, 0, 0, 0);
                txtDescription.setLayoutParams(textParams);
                tableRow.addView(txtDescription);

                tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tableParams.setMargins(2, 0, 2, 0); // left, top, right, bottom
                tableRow.setLayoutParams(tableParams);
                featureTable.addView(tableRow);
            }
            count++;
        }

        if (sheet.getCharClass().getArmorProficiencies().size() > 0 && !"".equals(sheet.getCharClass().getArmorProficiencies().get(0))) {
            tableRow = new TableRow(this);
            tableRow.setOrientation(TableRow.VERTICAL);
            if (count % 2 == 0)
                tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
            else
                tableRow.setBackground(getDrawable(R.drawable.layout_line));

            txtDescription = new TextView(this);
            txtDescription.setText(R.string.armorProf);
            txtDescription.setTextAppearance(R.style.tableHeader);
            txtDescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
            textParams.setMargins(5, 2, 2, 2);
            txtDescription.setLayoutParams(textParams);
            tableRow.addView(txtDescription);

            tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableParams.setMargins(2, 0, 2, 0); // left, top, right, bottom
            tableRow.setLayoutParams(tableParams);
            featureTable.addView(tableRow);

            for (String armorProf : sheet.getCharClass().getArmorProficiencies()) {
                tableRow = new TableRow(this);
                tableRow.setOrientation(TableRow.VERTICAL);
                if (count % 2 == 0)
                    tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
                else
                    tableRow.setBackground(getDrawable(R.drawable.layout_line));

                txtDescription = new TextView(this);
                txtDescription.setText(getString(getResources().getIdentifier(armorProf, "string", getPackageName())));
                txtDescription.setTextAppearance(R.style.tableItem);
                txtDescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
                textParams.setMargins(50, 0, 0, 0);
                txtDescription.setLayoutParams(textParams);
                tableRow.addView(txtDescription);

                tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tableParams.setMargins(2, 0, 2, 0); // left, top, right, bottom
                tableRow.setLayoutParams(tableParams);
                featureTable.addView(tableRow);
            }
            count++;
        }

        if (sheet.getCharClass().getWeaponProficiencies().size() > 0 && !"".equals(sheet.getCharClass().getWeaponProficiencies().get(0))) {
            tableRow = new TableRow(this);
            tableRow.setOrientation(TableRow.VERTICAL);
            if (count % 2 == 0)
                tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
            else
                tableRow.setBackground(getDrawable(R.drawable.layout_line));

            txtDescription = new TextView(this);
            txtDescription.setText(R.string.weaponProf);
            txtDescription.setTextAppearance(R.style.tableHeader);
            txtDescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
            textParams.setMargins(5, 2, 2, 2);
            txtDescription.setLayoutParams(textParams);
            tableRow.addView(txtDescription);

            tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableParams.setMargins(2, 0, 2, 0); // left, top, right, bottom
            tableRow.setLayoutParams(tableParams);
            featureTable.addView(tableRow);

            for (String weaponProf : sheet.getCharClass().getWeaponProficiencies()) {
                tableRow = new TableRow(this);
                tableRow.setOrientation(TableRow.VERTICAL);
                if (count % 2 == 0)
                    tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
                else
                    tableRow.setBackground(getDrawable(R.drawable.layout_line));

                txtDescription = new TextView(this);
                txtDescription.setText(getString(getResources().getIdentifier(weaponProf, "string", getPackageName())));
                txtDescription.setTextAppearance(R.style.tableItem);
                txtDescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
                textParams.setMargins(50, 0, 0, 0);
                txtDescription.setLayoutParams(textParams);
                tableRow.addView(txtDescription);

                tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
                tableParams.setMargins(2, 0, 2, 0); // left, top, right, bottom
                tableRow.setLayoutParams(tableParams);
                featureTable.addView(tableRow);
            }
        }
    }

    private void refreshSpells() {
        spellTable.removeAllViews();
        SheetSpells sheetSpells = sheet.getSpells();
        ArrayList<Spell> spellList = new ArrayList<>();
        spellList.addAll(sheetSpells.getCantrips());
        spellList.addAll(sheetSpells.getLevel1());
        spellList.addAll(sheetSpells.getLevel2());
        spellList.addAll(sheetSpells.getLevel3());
        spellList.addAll(sheetSpells.getLevel4());
        spellList.addAll(sheetSpells.getLevel5());
        spellList.addAll(sheetSpells.getLevel6());
        spellList.addAll(sheetSpells.getLevel7());
        spellList.addAll(sheetSpells.getLevel8());
        spellList.addAll(sheetSpells.getLevel9());

        TableRow tableRow = new TableRow(this);
        tableRow.setOrientation(TableRow.HORIZONTAL);
        tableRow.setBackground(getDrawable(R.drawable.layout_line));

        TextView txtAttack = new TextView(this);
        txtAttack.setText(R.string.name);
        txtAttack.setTextAppearance(R.style.tableHeader);
        txtAttack.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        TableRow.LayoutParams textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
        txtAttack.setLayoutParams(textParams);
        tableRow.addView(txtAttack);

        TextView txtRange = new TextView(this);
        txtRange.setText(R.string.reach);
        txtRange.setTextAppearance(R.style.tableHeader);
        txtRange.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtRange.setLayoutParams(textParams);
        tableRow.addView(txtRange);

        TextView txtHit = new TextView(this);
        txtHit.setText(R.string.hit);
        txtHit.setTextAppearance(R.style.tableHeader);
        txtHit.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtHit.setLayoutParams(textParams);
        tableRow.addView(txtHit);

        TextView txDamage = new TextView(this);
        txDamage.setText(R.string.damage);
        txDamage.setTextAppearance(R.style.tableHeader);
        txDamage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txDamage.setLayoutParams(textParams);
        tableRow.addView(txDamage);

        TextView txtEmpty = new TextView(this);
        txtEmpty.setTextAppearance(R.style.tableHeader);
        txtEmpty.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtEmpty.setLayoutParams(textParams);
        tableRow.addView(txtEmpty);

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tableParams.setMargins(10, 2, 2, 2); // left, top, right, bottom
        tableRow.setLayoutParams(tableParams);
        spellTable.addView(tableRow);

        int count = 0;
        for (final Spell spell: spellList){
            tableRow = new TableRow(this);
            tableRow.setOrientation(TableRow.HORIZONTAL);
            if (count % 2 == 0)
                tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
            else
                tableRow.setBackground(getDrawable(R.drawable.layout_line));
            count++;

            txtAttack = new TextView(this);
            txtAttack.setText(spell.getName());
            txtAttack.setTextAppearance(R.style.tableItem);
            txtAttack.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
            txtAttack.setLayoutParams(textParams);
            tableRow.addView(txtAttack);

            txtRange = new TextView(this);
            String reach;
            if (spell.getRange() > 0)
                reach = spell.getRange() + "ft";
            else
                reach = "Toque";
            txtRange.setText(reach);
            txtRange.setTextAppearance(R.style.tableItem);
            txtRange.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtRange.setLayoutParams(textParams);
            tableRow.addView(txtRange);

            txtHit = new TextView(this);
            String hit = "+" + String.valueOf(sheet.getAttribute(Program.MODIFIER, Program.INTELLIGENCE));
            txtHit.setText(hit);
            txtHit.setTextAppearance(R.style.tableItem);
            txtHit.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtHit.setLayoutParams(textParams);
            tableRow.addView(txtHit);

            txDamage = new TextView(this);
            String damage;
            if (spell.getDie() > 0)
                damage = spell.getDieNumber() + "x d" + spell.getDie();
            else
                damage = "N/D";
            txDamage.setText(damage);
            txDamage.setTextAppearance(R.style.tableItem);
            txDamage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txDamage.setLayoutParams(textParams);
            tableRow.addView(txDamage);

            txtEmpty = new TextView(this);
            txtEmpty.setTextAppearance(R.style.tableItem);
            txtEmpty.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtEmpty.setLayoutParams(textParams);
            tableRow.addView(txtEmpty);

            tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableParams.setMargins(10, 2, 2, 2); // left, top, right, bottom
            tableRow.setLayoutParams(tableParams);

//            tableRow.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    rollDie(spell.getWeapon().getDie(), action.getWeapon().getDieNumber(), sheet.getAttribute(Program.MODIFIER, Program.STRENGTH), "Dano - " + action.getDescription());
//                    rollDie(20, 1, sheet.getAttribute(Program.MODIFIER, Program.STRENGTH), "Ataque - " +action.getDescription());
//                }
//            });

            spellTable.addView(tableRow);
        }
    }

    private void refreshActions() {
        ArrayList<Action> actions = new ArrayList<>();

        for (Equipment equipment: sheet.getEquipment()){
            if (equipment.getWeapon() != null) {
                Weapon weapon = equipment.getWeapon();
                Action action = new Action();
                action.setType(Program.ATTACK);
                action.setUses(-1);
                action.setWeapon(weapon);
                action.setDescription(weapon.getName());

                actions.add(action);
            }
        }

        actionTable.removeAllViews();

        TableRow tableRow = new TableRow(this);
        tableRow.setOrientation(TableRow.HORIZONTAL);
        tableRow.setBackground(getDrawable(R.drawable.layout_line));

        TextView txtAttack = new TextView(this);
        txtAttack.setText(R.string.attack);
        txtAttack.setTextAppearance(R.style.tableHeader);
        txtAttack.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        TableRow.LayoutParams textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
        txtAttack.setLayoutParams(textParams);
        tableRow.addView(txtAttack);

        TextView txtRange = new TextView(this);
        txtRange.setText(R.string.reach);
        txtRange.setTextAppearance(R.style.tableHeader);
        txtRange.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtRange.setLayoutParams(textParams);
        tableRow.addView(txtRange);

        TextView txtHit = new TextView(this);
        txtHit.setText(R.string.hit);
        txtHit.setTextAppearance(R.style.tableHeader);
        txtHit.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtHit.setLayoutParams(textParams);
        tableRow.addView(txtHit);

        TextView txDamage = new TextView(this);
        txDamage.setText(R.string.damage);
        txDamage.setTextAppearance(R.style.tableHeader);
        txDamage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txDamage.setLayoutParams(textParams);
        tableRow.addView(txDamage);

        TextView txtEmpty = new TextView(this);
        txtEmpty.setTextAppearance(R.style.tableHeader);
        txtEmpty.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtEmpty.setLayoutParams(textParams);
        tableRow.addView(txtEmpty);

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tableParams.setMargins(10, 2, 2, 2); // left, top, right, bottom
        tableRow.setLayoutParams(tableParams);
        actionTable.addView(tableRow);

        int count = 0;
        for (final Action action: actions){
            tableRow = new TableRow(this);
            tableRow.setOrientation(TableRow.HORIZONTAL);
            if (count % 2 == 0)
                tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
            else
                tableRow.setBackground(getDrawable(R.drawable.layout_line));
            count++;

            txtAttack = new TextView(this);
            txtAttack.setText(action.getDescription());
            txtAttack.setTextAppearance(R.style.tableItem);
            txtAttack.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
            txtAttack.setLayoutParams(textParams);
            tableRow.addView(txtAttack);

            txtRange = new TextView(this);
            String reach;
            if (action.getWeapon().getMinRange() != action.getWeapon().getMaxRange())
                reach = action.getWeapon().getMinRange() + "/" + action.getWeapon().getMaxRange() + "ft";
            else
                reach = String.valueOf(action.getWeapon().getMinRange()) + "ft";
            txtRange.setText(reach);
            txtRange.setTextAppearance(R.style.tableItem);
            txtRange.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtRange.setLayoutParams(textParams);
            tableRow.addView(txtRange);

            txtHit = new TextView(this);
            String hit = "+" + String.valueOf(sheet.getAttribute(Program.MODIFIER, Program.STRENGTH));
            txtHit.setText(hit);
            txtHit.setTextAppearance(R.style.tableItem);
            txtHit.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtHit.setLayoutParams(textParams);
            tableRow.addView(txtHit);

            txDamage = new TextView(this);
            String damage = action.getWeapon().getDieNumber() + "x d" + action.getWeapon().getDie();
            txDamage.setText(damage);
            txDamage.setTextAppearance(R.style.tableItem);
            txDamage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txDamage.setLayoutParams(textParams);
            tableRow.addView(txDamage);

            txtEmpty = new TextView(this);
            txtEmpty.setTextAppearance(R.style.tableItem);
            txtEmpty.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtEmpty.setLayoutParams(textParams);
            tableRow.addView(txtEmpty);

            tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableParams.setMargins(10, 2, 2, 2); // left, top, right, bottom
            tableRow.setLayoutParams(tableParams);

            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rollDie(action.getWeapon().getDie(), action.getWeapon().getDieNumber(), sheet.getAttribute(Program.MODIFIER, Program.STRENGTH), "Dano - " + action.getDescription());
                    rollDie(20, 1, sheet.getAttribute(Program.MODIFIER, Program.STRENGTH), "Ataque - " +action.getDescription());
                }
            });

            actionTable.addView(tableRow);
        }

        ArrayList<Spell> spells = new ArrayList<>();
        spells.addAll(sheet.getSpells().getCantrips());
        spells.addAll(sheet.getSpells().getLevel1());
        spells.addAll(sheet.getSpells().getLevel2());
        spells.addAll(sheet.getSpells().getLevel3());
        spells.addAll(sheet.getSpells().getLevel4());
        spells.addAll(sheet.getSpells().getLevel5());
        spells.addAll(sheet.getSpells().getLevel6());
        spells.addAll(sheet.getSpells().getLevel7());
        spells.addAll(sheet.getSpells().getLevel8());
        spells.addAll(sheet.getSpells().getLevel9());

        for (Spell spell: spells){
            Action action = new Action();
            action.setType(Program.ATTACK);
            action.setUses(-1);
            action.setSpell(spell);
            action.setDescription(spell.getName());

            actions.add(action);
        }

        for (Action action: actions){
            //TODO
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void refreshEquipment() {

        equipmentTable.removeAllViews();

        TableRow tableRow = new TableRow(this);
        tableRow.setOrientation(TableRow.HORIZONTAL);
        tableRow.setBackground(getDrawable(R.drawable.layout_line));

        TextView txtName = new TextView(this);
        txtName.setText(R.string.name);
        txtName.setTextAppearance(R.style.tableHeader);
        txtName.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        TableRow.LayoutParams textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
        txtName.setLayoutParams(textParams);
        tableRow.addView(txtName);

        TextView txtQuantity = new TextView(this);
        txtQuantity.setText(R.string.quantity);
        txtQuantity.setTextAppearance(R.style.tableHeader);
        txtQuantity.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtQuantity.setLayoutParams(textParams);
        tableRow.addView(txtQuantity);

        TextView txtWeight = new TextView(this);
        txtWeight.setText(R.string.weight);
        txtWeight.setTextAppearance(R.style.tableHeader);
        txtWeight.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtWeight.setLayoutParams(textParams);
        tableRow.addView(txtWeight);

        TextView txtCost = new TextView(this);
        txtCost.setText(R.string.cost);
        txtCost.setTextAppearance(R.style.tableHeader);
        txtCost.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        txtCost.setLayoutParams(textParams);
        tableRow.addView(txtCost);

        ImageView addImage = new ImageView(this);
        addImage.setBackground(getDrawable(R.drawable.add_box));

        addImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    final ArrayList<Item> items = new ArrayList<>();
                    items.addAll(Objects.requireNonNull(Item.getItemList(MainActivity.this)));
                    items.addAll(Objects.requireNonNull(Weapon.getWeaponList(MainActivity.this)));
                    items.addAll(Objects.requireNonNull(Armor.getArmorList(MainActivity.this)));

                    ArrayList<String> itemName = new ArrayList<>();
                    for (Item item : items) {
                        itemName.add(item.getName());
                    }

                    final ArrayAdapter<String> adp = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, itemName);
                    final Spinner sp = new Spinner(MainActivity.this);
                    sp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    sp.setAdapter(adp);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Equipamento")
                            .setView(sp)
                            .setMessage("")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    int index = sp.getSelectedItemPosition();

                                    Item item = items.get(index);

                                    sheet.addEquipment(item, 1);
                                    refreshEquipment();
                                    String msg = item.getName() + " adicionado(a) ao equipamento.";
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            }).setNeutralButton("Criar item", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, EquipmentEditActivity.class);
                            startActivity(intent);
                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }
                    }).show();
                } catch (Exception ex) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Erro")
                            .setMessage("Erro ao buscar item. \n" + ex.getMessage())
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    dialoginterface.cancel();
                                }
                            }).show();
                }

                return false;
            }
        });

        textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        addImage.setLayoutParams(textParams);
        tableRow.addView(addImage);

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tableParams.setMargins(10, 2, 2, 2); // left, top, right, bottom
        tableRow.setLayoutParams(tableParams);
        equipmentTable.addView(tableRow);

        int count = 0;
        for (final Equipment equipment: sheet.getEquipment()){
            final Item item;
            if (equipment.getWeapon() != null)
                item = equipment.getWeapon();
            else if (equipment.getArmor() != null)
                item = equipment.getArmor();
            else
                item = equipment.getItem();

            tableRow = new TableRow(this);
            tableRow.setOrientation(TableRow.HORIZONTAL);
            if (count % 2 == 0)
                tableRow.setBackground(getDrawable(R.drawable.layout_line_light));
            else
                tableRow.setBackground(getDrawable(R.drawable.layout_line));
            count++;

            txtName = new TextView(this);
            txtName.setText(item.getName());
            txtName.setTextAppearance(R.style.tableItem);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 2f);
            txtName.setLayoutParams(textParams);
            tableRow.addView(txtName);

            txtQuantity = new TextView(this);
            txtQuantity.setText(String.valueOf(equipment.getQuantity()));
            txtQuantity.setTextAppearance(R.style.tableItem);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtQuantity.setLayoutParams(textParams);
            tableRow.addView(txtQuantity);

            txtWeight = new TextView(this);
            txtWeight.setText(String.format(Locale.getDefault(), "%.0f", item.getWeight()));
            txtWeight.setTextAppearance(R.style.tableItem);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtWeight.setLayoutParams(textParams);
            tableRow.addView(txtWeight);

            txtCost = new TextView(this);
            String text = String.format(Locale.getDefault(), "%.0f", item.getCost()) + " " + item.getCoin();
            txtCost.setText(text);
            txtCost.setTextAppearance(R.style.tableItem);
            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            txtCost.setLayoutParams(textParams);
            tableRow.addView(txtCost);

            ImageView moreImage = new ImageView(this);
            moreImage.setBackground(getDrawable(R.drawable.more_icon));

            moreImage.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    final EditText edtQuantity = new EditText(MainActivity.this);
                    edtQuantity.setText(String.valueOf(equipment.getQuantity()));
                    edtQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
                    dialog.setTitle(item.getName())
                            .setView(edtQuantity)
                            .setMessage("Quantidade: ")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    if (edtQuantity.getText().length() > 0)
                                        equipment.setQuantity(Integer.parseInt(edtQuantity.getText().toString()));
                                    refreshEquipment();
                                    String msg = item.getName() + " adicionado(a) ao equipamento.";
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            }).setNeutralButton("Remover item", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ArrayList<Equipment> equipmentList = sheet.getEquipment();
                            equipmentList.remove(equipment);
                            refreshEquipment();
                            dialog.cancel();
                        }
                    }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            dialoginterface.cancel();
                        }
                    }).show();
                    return false;
                }
            });

            textParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            moreImage.setLayoutParams(textParams);
            tableRow.addView(moreImage);

            tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            tableParams.setMargins(10, 2, 2, 2); // left, top, right, bottom
            tableRow.setLayoutParams(tableParams);
            equipmentTable.addView(tableRow);
        }
    }

    private void changeLifeBar(){
        int percentage = (sheet.getHitPoints()*100/sheet.getMaxHitPoints())*100;
        String hp = sheet.getHitPoints() + "/" + sheet.getMaxHitPoints();
        txtHP.setText(hp);
        txtHP.getBackground().setLevel(percentage);
    }

    private void changeExpBar(){
        int maxExp = 0;
        switch (sheet.getLevel()){
            case 1:
                maxExp = 300;
                break;
            case 2:
                maxExp = 900;
                break;
            case 3:
                maxExp = 2700;
                break;
            case 4:
                maxExp = 6500;
                break;
            case 5:
                maxExp = 14000;
                break;
            case 6:
                maxExp = 23000;
                break;
            case 7:
                maxExp = 34000;
                break;
            case 8:
                maxExp = 48000;
                break;
            case 9:
                maxExp = 64000;
                break;
            case 10:
                maxExp = 85000;
                break;
            case 11:
                maxExp = 100000;
                break;
            case 12:
                maxExp = 120000;
                break;
            case 13:
                maxExp = 140000;
                break;
            case 14:
                maxExp = 165000;
                break;
            case 15:
                maxExp = 195000;
                break;
            case 16:
                maxExp = 225000;
                break;
            case 17:
                maxExp = 265000;
                break;
            case 18:
                maxExp = 305000;
                break;
            case 19:
                maxExp = 355000;
                break;
        }


        long percentage = 0;
        if (sheet.getExp() > 0)
            percentage = (sheet.getExp()*100/maxExp)*100;
        String exp = sheet.getExp() + "/" + maxExp;
        txtContExp.setText(exp);
        txtContExp.getBackground().setLevel((int) percentage);
    }

    private void createItem(){
        Intent intent = new Intent(MainActivity.this, EquipmentEditActivity.class);
        Item item = new Item();
        intent.putExtra("item", item);
        startActivityForResult(intent, CREATE_ITEM_REQUEST);
    }

    private void touchEvent(MotionEvent event, int dieNumber, int diceQuantity, int mod, String observation){
        long eventDuration = event.getEventTime() - event.getDownTime();
        if (eventDuration > 400)
            rollDie(dieNumber, diceQuantity, mod, observation);
    }

    private void rollDie(int dieNumber, int dieQuantity, int mod, String observation){
        int result = 0;
        StringBuilder msg = new StringBuilder(observation + "\n" + dieQuantity + "x d" + dieNumber + ": ");
        Random random = new Random();
        int diceCount = dieQuantity;
        while (diceCount > 0) {
            int number = random.nextInt(dieNumber)+1;
            if (diceCount > 1)
                msg.append(number).append(" + ");
            else
                msg.append(number);
            result += number;
            diceCount--;
        }
        if (mod > 0) msg.append(" + ").append(mod);
        result += mod;
        msg.append(" = ").append(result);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Jogada")
                .setMessage(msg)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                }).show();

        DiceRollRegistry diceRoll = new DiceRollRegistry(dieQuantity, "d" + String.valueOf(dieNumber), mod, observation, result);
        sheet.addDiceRollRegistry(diceRoll);
    }

    private void addDie(String die){
        int count = 0;
        if (dice.get(die) != null) {
            count = dice.get(die);
        } else
            dice = new HashMap<>();
        count++;
        dice.put(die, count);

        String msg = count + "x " + die;
        txtRoll.setText(msg);
    }

    private void rollDice(){
        int result = 0;

        StringBuilder msg = new StringBuilder("Jogada Personalizada");
        Random random = new Random();

        int mod = 0;
        if (edtMod.getText().length() > 0)
            mod = Integer.parseInt(edtMod.getText().toString());

        for (String die: dice.keySet()) {
            int dieQuantity = 0;
            int dieNumber = 0;

            if (dice.get(die) != null) {
                dieNumber = Integer.parseInt(die.replace("d", ""));
                dieQuantity = dice.get(die);
            }

            msg.append("\n").append(dieQuantity).append("x d").append(dieNumber).append(": ");

            int diceCount = dieQuantity;
            while (diceCount > 0) {
                int number = random.nextInt(dieNumber) + 1;
                if (diceCount > 1)
                    msg.append(number).append(" + ");
                else
                    msg.append(number);
                result += number;
                diceCount--;
            }

            DiceRollRegistry diceRoll = new DiceRollRegistry(dieQuantity, "d" + String.valueOf(dieNumber), mod, "Jogada Personalizada", result);
            sheet.addDiceRollRegistry(diceRoll);
            clearDice();
        }

        if (mod > 0) msg.append(" + ").append(mod);
        result += mod;
        msg.append(" = ").append(result);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Jogada")
                .setMessage(msg)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                }).show();
    }

    private void clearDice(){
        dice.clear();
        txtRoll.setText(R.string.empty);
        edtMod.setText(R.string.zero);
    }

    private void setSkillMod(TextView view, String skill, boolean isChecked){
        if (isChecked) {
            String value = String.valueOf(sheet.getSkillValue(skill) + sheet.getProficiency());
            view.setText(value);
        } else
            view.setText(String.valueOf(sheet.getSkillValue(skill)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                //do something
            }
        }
    }

    @Override
    public void onBackPressed() {
        saveSheet();
        Intent intent = new Intent(MainActivity.this, SheetPickActivity.class);
        startActivity(intent);
        finish();

        super.onBackPressed();
    }

    private void saveSheet(){
        ArrayList<Sheet> sheets = (ArrayList<Sheet>) Program.readFile(this, "sheet.json");
        if (sheets == null)
            sheets = new ArrayList<>();
        for (Sheet savedSheet: sheets){
            if (sheet.getName().equals(savedSheet.getName())) {
                sheets.remove(savedSheet);
                break;
            }
        }
        sheets.add(sheet);
        Program.createFile(this, "sheet.json", sheets);
    }

    private void temporarySheet(){
        sheet = new Sheet();
        sheet.setName("Rohan");
        sheet.setExp(4506L);
        sheet.setGender("Homem");
        sheet.setGold(10);
        sheet.setAlignment("Leal neutro");
        sheet.setAge(40);
        sheet.setHeight("1.50");
        sheet.setSize("Médio");
        sheet.setEyes("Castanho");
        sheet.setFaith("");
        sheet.setAppearance("");
        sheet.setSpeed(30);
        sheet.setInitiative(2);
        sheet.setHair("");
        sheet.setMaxHitPoints(10);
        sheet.setHitPoints(10);
        sheet.setArmor(16);
        sheet.setProficiency(2);
        sheet.setPassivePerception(1);
        sheet.setPassiveInvestigation(1);
        sheet.setPassiveInsight(1);

        sheet.setAttribute(Program.SCORE, Program.STRENGTH, 15);
        sheet.setAttribute(Program.SCORE, Program.DEXTERITY, 14);
        sheet.setAttribute(Program.SCORE, Program.CONSTITUTION, 13);
        sheet.setAttribute(Program.SCORE, Program.INTELLIGENCE, 8);
        sheet.setAttribute(Program.SCORE, Program.WISDOM, 10);
        sheet.setAttribute(Program.SCORE, Program.CHARISMA, 12);

        sheet.setAttribute(Program.MODIFIER, Program.STRENGTH, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.STRENGTH)));
        sheet.setAttribute(Program.MODIFIER, Program.DEXTERITY, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.DEXTERITY)));
        sheet.setAttribute(Program.MODIFIER, Program.CONSTITUTION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CONSTITUTION)));
        sheet.setAttribute(Program.MODIFIER, Program.INTELLIGENCE, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setAttribute(Program.MODIFIER, Program.WISDOM, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setAttribute(Program.MODIFIER, Program.CHARISMA, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));

        sheet.setAttribute(Program.SAVING, Program.STRENGTH, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.STRENGTH)));
        sheet.setAttribute(Program.SAVING, Program.DEXTERITY, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.DEXTERITY)));
        sheet.setAttribute(Program.SAVING, Program.CONSTITUTION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CONSTITUTION)));
        sheet.setAttribute(Program.SAVING, Program.INTELLIGENCE, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setAttribute(Program.SAVING, Program.WISDOM, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setAttribute(Program.SAVING, Program.CHARISMA, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));

        sheet.setSkill(Program.ATHLETICS, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.STRENGTH)));
        sheet.setSkillProficiency(Program.ATHLETICS, true);

        sheet.setSkill(Program.ACROBATICS, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.DEXTERITY)));
        sheet.setSkillProficiency(Program.ACROBATICS, false);

        sheet.setSkill(Program.ARCANA, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setSkillProficiency(Program.ARCANA, false);

        sheet.setSkill(Program.PERFORMANCE, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));
        sheet.setSkillProficiency(Program.PERFORMANCE, true);

        sheet.setSkill(Program.DECEPTION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));
        sheet.setSkillProficiency(Program.DECEPTION, false);

        sheet.setSkill(Program.STEALTH, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.DEXTERITY)));
        sheet.setSkillProficiency(Program.STEALTH, false);

        sheet.setSkill(Program.HISTORY, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setSkillProficiency(Program.HISTORY, false);

        sheet.setSkill(Program.INSIGHT, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setSkillProficiency(Program.INSIGHT, false);

        sheet.setSkill(Program.INTIMIDATION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));
        sheet.setSkillProficiency(Program.INTIMIDATION, false);

        sheet.setSkill(Program.INVESTIGATION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setSkillProficiency(Program.INVESTIGATION, false);

        sheet.setSkill(Program.ANIMALHANDLING, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setSkillProficiency(Program.ANIMALHANDLING, true);

        sheet.setSkill(Program.MEDICINE, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setSkillProficiency(Program.MEDICINE, false);

        sheet.setSkill(Program.NATURE, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setSkillProficiency(Program.NATURE, false);

        sheet.setSkill(Program.PERCEPTION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setSkillProficiency(Program.PERCEPTION, false);

        sheet.setSkill(Program.PERSUASION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.CHARISMA)));
        sheet.setSkillProficiency(Program.PERSUASION, true);

        sheet.setSkill(Program.SLEIGHTOFHAND, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.DEXTERITY)));
        sheet.setSkillProficiency(Program.SLEIGHTOFHAND, false);

        sheet.setSkill(Program.RELIGION, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.INTELLIGENCE)));
        sheet.setSkillProficiency(Program.RELIGION, false);

        sheet.setSkill(Program.SURVIVAL, Program.getModValue(sheet.getAttribute(Program.SCORE, Program.WISDOM)));
        sheet.setSkillProficiency(Program.SURVIVAL, false);

        sheet.setDiceRollRegistry(new ArrayList<DiceRollRegistry>());
        ArrayList<Equipment> equipment = new ArrayList<>();
        sheet.setEquipment(equipment);
    }
}
