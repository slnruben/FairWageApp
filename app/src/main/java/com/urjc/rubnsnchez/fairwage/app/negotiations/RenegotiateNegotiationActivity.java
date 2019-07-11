package com.urjc.rubnsnchez.fairwage.app.negotiations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.app.MyApplication;
import com.urjc.rubnsnchez.fairwage.app.contacts.Contact;
import com.urjc.rubnsnchez.fairwage.app.contacts.ContactListActivity;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Objects;

import static com.urjc.rubnsnchez.fairwage.app.MyApplication.ALGORITHM;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.CODE_PRIVATE;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.CODE_PUBLIC;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.CREATE_NEGOTIATION;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.GET_CONTACT_PUBLIC_KEY;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.NEGOTIATION;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REFUSED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SEARCH_NEGOTIATIONS;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SENDED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SUCCESS;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.UPDATE_NEGOTIATION;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.getUserData;
import static com.urjc.rubnsnchez.fairwage.app.common.CipherUtil.encryptBase64;
import static com.urjc.rubnsnchez.fairwage.app.common.CipherUtil.stringToPublicKey;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.checkPrivacy;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.isNetworkAvailable;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.sendDataPOST;

public class RenegotiateNegotiationActivity extends Activity {
    Switch nameSwitch;
    Switch contactNameSwitch;
    Switch surnamesSwitch;
    Switch contactSurnamesSwitch;
    Switch jobSwitch;
    Switch contactJobSwitch;
    Switch companySwitch;
    Switch contactCompanySwitch;
    Switch emailSwitch;
    Switch contactEmailSwitch;
    Switch telephoneSwitch;
    Switch contactTelephoneSwitch;
    Switch universitySwitch;
    Switch contactUniversitySwitch;
    Switch careerSwitch;
    Switch contactCareerSwitch;
    Switch sector1Switch;
    Switch contactSector1Switch;
    Switch sector2Switch;
    Switch contactSector2Switch;
    Switch experienceSwitch;
    Switch contactExperienceSwitch;
    Switch languagesSwitch;
    Switch contactLanguagesSwitch;
    Switch knowledgesSwitch;
    Switch contactKnowledgesSwitch;

    Contact user;
    Negotiation negotiation;
    byte[] contactPublicKey;

    private void setSwitches() {
        if (!negotiation.getrName().isEmpty()) {
            nameSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrName()))) {
                nameSwitch.setClickable(false);
            }
        }
        if (!negotiation.getrSurnames().isEmpty()) {
            surnamesSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrSurnames()))) {
                surnamesSwitch.setClickable(false);
            }
        }
        if (!negotiation.getrJob().isEmpty()) {
            jobSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrJob()))) {
                jobSwitch.setClickable(false);
            }
        }
        if (!negotiation.getrCompany().isEmpty()) {
            companySwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrCompany()))) {
                companySwitch.setClickable(false);
            }
        }
        if (!negotiation.getrEmail().isEmpty()) {
            emailSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrEmail()))) {
                emailSwitch.setClickable(false);
            }
        }
        if (!negotiation.getrTelephone().isEmpty()) {
            telephoneSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrTelephone()))) {
                telephoneSwitch.setClickable(false);
            }
        }
        if (!negotiation.getrUniversity().isEmpty()) {
            universitySwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrUniversity()))) {
                universitySwitch.setClickable(false);
            }
        }
        if (!negotiation.getrCareer().isEmpty()) {
            careerSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrSurnames()))) {
                careerSwitch.setClickable(false);
            }
        }
        if (!negotiation.getrSector1().isEmpty()) {
            sector1Switch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrSector1()))) {
                surnamesSwitch.setClickable(false);
            }
        }
        if (!negotiation.getrSector2().isEmpty()) {
            sector2Switch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrSector2()))) {
                sector2Switch.setClickable(false);
            }
        }

        if (!negotiation.getrExperience().isEmpty()) {
            experienceSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrExperience()))) {
                experienceSwitch.setClickable(false);
            }
        }

        if (!negotiation.getrLanguages().isEmpty()) {
            languagesSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrLanguages()))) {
                languagesSwitch.setClickable(false);
            }
        }
        if (!negotiation.getrKnowledges().isEmpty()) {
            knowledgesSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrKnowledges()))) {
                knowledgesSwitch.setClickable(false);
            }
        }
        if (!negotiation.getoName().isEmpty()) {
            contactNameSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoName()))) {
                contactNameSwitch.setClickable(false);
            }
        }
        if (!negotiation.getoSurnames().isEmpty()) {
            contactSurnamesSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getrSurnames()))) {
                contactSurnamesSwitch.setClickable(false);
            }
        }
        if (!negotiation.getoJob().isEmpty()) {
            contactJobSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoJob()))) {
                contactJobSwitch.setClickable(false);
            }
        }
        if (!negotiation.getoCompany().isEmpty()) {
            contactCompanySwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoCompany()))) {
                contactCompanySwitch.setClickable(false);
            }
        }
        if (!negotiation.getoEmail().isEmpty()) {
            contactEmailSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoEmail()))) {
                contactEmailSwitch.setClickable(false);
            }
        }
        if (!negotiation.getoTelephone().isEmpty()) {
            contactTelephoneSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoTelephone()))) {
                contactTelephoneSwitch.setClickable(false);
            }
        }
        if (!negotiation.getoUniversity().isEmpty()) {
            contactUniversitySwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoUniversity()))) {
                contactUniversitySwitch.setClickable(false);
            }
        }
        if (!negotiation.getoCareer().isEmpty()) {
            contactCareerSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoCareer()))) {
                contactCareerSwitch.setClickable(false);
            }
        }
        if (!negotiation.getoSector1().isEmpty()) {
            contactSector1Switch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoSector1()))) {
                contactSector1Switch.setClickable(false);
            }
        }
        if (!negotiation.getoSector2().isEmpty()) {
            contactSector2Switch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoSector2()))) {
                contactSector2Switch.setClickable(false);
            }
        }
        if (!negotiation.getoExperience().isEmpty()) {
            contactExperienceSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoExperience()))) {
                contactExperienceSwitch.setClickable(false);
            }
        }
        if (!negotiation.getoLanguages().isEmpty()) {
            contactLanguagesSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoLanguages()))) {
                contactLanguagesSwitch.setClickable(false);
            }
        }
        if (!negotiation.getoKnowledges().isEmpty()) {
            contactKnowledgesSwitch.setChecked(true);
            if (!"".equals(checkPrivacy(negotiation.getoKnowledges()))) {
                contactKnowledgesSwitch.setClickable(false);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.negotiation_request);
        nameSwitch = findViewById(R.id.nameSwitch);
        contactNameSwitch = findViewById(R.id.contactNameSwitch);
        surnamesSwitch = findViewById(R.id.surnamesSwitch);
        contactSurnamesSwitch = findViewById(R.id.contactSurnamesSwitch);
        jobSwitch = findViewById(R.id.jobSwitch);
        contactJobSwitch = findViewById(R.id.contactJobSwitch);
        companySwitch = findViewById(R.id.companySwitch);
        contactCompanySwitch = findViewById(R.id.contactCompanySwitch);
        emailSwitch = findViewById(R.id.emailSwitch);
        contactEmailSwitch = findViewById(R.id.contactEmailSwitch);
        telephoneSwitch = findViewById(R.id.telephoneSwitch);
        contactTelephoneSwitch = findViewById(R.id.contactTelephoneSwitch);
        universitySwitch = findViewById(R.id.universitySwitch);
        contactUniversitySwitch = findViewById(R.id.contactUniversitySwitch);
        careerSwitch = findViewById(R.id.careerSwitch);
        contactCareerSwitch = findViewById(R.id.contactCareerSwitch);
        sector1Switch = findViewById(R.id.sector1Switch);
        contactSector1Switch = findViewById(R.id.contactSector1Switch);
        sector2Switch = findViewById(R.id.sector2Switch);
        contactSector2Switch = findViewById(R.id.contactSector2Switch);
        experienceSwitch = findViewById(R.id.experienceSwitch);
        contactExperienceSwitch = findViewById(R.id.contactExperienceSwitch);
        languagesSwitch = findViewById(R.id.languagesSwitch);
        contactLanguagesSwitch = findViewById(R.id.contactLanguagesSwitch);
        knowledgesSwitch = findViewById(R.id.knowledgesSwitch);
        contactKnowledgesSwitch = findViewById(R.id.contactKnowledgesSwitch);
        Button sendButton = findViewById(R.id.sendButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        sendButton.setOnClickListener(new sendButtonListener());
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final Context context = getApplicationContext();
        String myUser = ((MyApplication) context).getUser();
        user = getUserData(context, myUser, NEGOTIATION);
        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        if (info != null) {
            negotiation = new Negotiation(info);
            contactPublicKey= Base64.decode((String) info.get(String.valueOf(R.string.publicKey)), Base64.NO_WRAP);
            setSwitches();
        }
    }

    /**
     * Comprueba si el usuario introducido en los campos concuerda con los datos de la BBDD del servidor,
     * si es correcto permite el acceso a la aplicacion y guarda el usuario para futuras sesiones.
     */
    private class sendButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int errors = 0;
            final Context context = getApplicationContext();
            final String urlServer = ((MyApplication) context).getUrlServer();
            final HashMap<String, String> postDataParams = new HashMap<>();
            postDataParams.put(context.getString(R.string.id), String.valueOf(negotiation.getIdServer()));
            postDataParams.put(context.getString(R.string.userCreator), user.getUser());
            postDataParams.put(context.getString(R.string.userReceptor), negotiation.getUserCreator());
            postDataParams.put(context.getString(R.string.oName), "");
            postDataParams.put(context.getString(R.string.oSurnames), "");
            postDataParams.put(context.getString(R.string.oEmail), "");
            postDataParams.put(context.getString(R.string.oTelephone), "");
            postDataParams.put(context.getString(R.string.oJob), "");
            postDataParams.put(context.getString(R.string.oCompany), "");
            postDataParams.put(context.getString(R.string.oUniversity), "");
            postDataParams.put(context.getString(R.string.oCareer), "");
            postDataParams.put(context.getString(R.string.oSector1), "");
            postDataParams.put(context.getString(R.string.oSector2), "");
            postDataParams.put(context.getString(R.string.oExperience), "");
            postDataParams.put(context.getString(R.string.oLanguages), "");
            postDataParams.put(context.getString(R.string.oKnowledges), "");
            postDataParams.put(context.getString(R.string.rName), "");
            postDataParams.put(context.getString(R.string.rSurnames), "");
            postDataParams.put(context.getString(R.string.rEmail), "");
            postDataParams.put(context.getString(R.string.rTelephone), "");
            postDataParams.put(context.getString(R.string.rJob), "");
            postDataParams.put(context.getString(R.string.rCompany), "");
            postDataParams.put(context.getString(R.string.rUniversity), "");
            postDataParams.put(context.getString(R.string.rCareer), "");
            postDataParams.put(context.getString(R.string.rSector1), "");
            postDataParams.put(context.getString(R.string.rSector2), "");
            postDataParams.put(context.getString(R.string.rExperience), "");
            postDataParams.put(context.getString(R.string.rLanguages), "");
            postDataParams.put(context.getString(R.string.rKnowledges), "");

            try {
                KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
                PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(contactPublicKey));

                if (nameSwitch.isChecked()) {
                    if (user.getName() != null && !user.getName().trim().isEmpty()) {
                        if (user.getNameVisibility() == 1) {
                            postDataParams.put(context.getString(R.string.oName), CODE_PUBLIC + user.getName());
                        } else {
                            postDataParams.put(context.getString(R.string.oName), CODE_PRIVATE + encryptBase64(publicKey, user.getName()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oNameNeeded), Toast.LENGTH_SHORT).show();
                    }
                }
                if (surnamesSwitch.isChecked()) {
                    if (user.getSurnames() != null && !user.getSurnames().trim().isEmpty()) {
                        if (user.getSurnamesVisibility() == 1) {
                            postDataParams.put(context.getString(R.string.oSurnames), CODE_PUBLIC + user.getSurnames());
                        } else {
                            postDataParams.put(context.getString(R.string.oSurnames), CODE_PRIVATE + encryptBase64(publicKey, user.getSurnames()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oSurnamesNeeded), Toast.LENGTH_SHORT).show();
                    }
                }
                if (emailSwitch.isChecked()) {
                    if (user.getEmail() != null && !user.getEmail().trim().isEmpty()) {
                        if (user.getEmailVisibility() == 1) {
                            postDataParams.put(context.getString(R.string.oEmail), CODE_PUBLIC + user.getEmail());
                        } else {
                            postDataParams.put(context.getString(R.string.oEmail), CODE_PRIVATE + encryptBase64(publicKey, user.getEmail()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oEmailNeeded), Toast.LENGTH_SHORT).show();
                    }
                }
                if (telephoneSwitch.isChecked()) {
                    if (user.getTelephone() != null && !user.getTelephone().trim().isEmpty()) {
                        if (user.getTelephoneVisibility() == 1) {
                            postDataParams.put(context.getString(R.string.oTelephone), CODE_PUBLIC + user.getTelephone());
                        } else {
                            postDataParams.put(context.getString(R.string.oTelephone), CODE_PRIVATE + encryptBase64(publicKey, user.getTelephone()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oTelephoneNeeded), Toast.LENGTH_SHORT).show();
                    }
                }
                if (jobSwitch.isChecked()) {
                    if (user.getJob() != null && !user.getJob().trim().isEmpty()) {
                        if (user.getJobVisibility() == 1) {
                            postDataParams.put(context.getString(R.string.oJob), CODE_PUBLIC + user.getJob());
                        } else {
                            postDataParams.put(context.getString(R.string.oJob), CODE_PRIVATE + encryptBase64(publicKey, user.getJob()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oJobNeeded), Toast.LENGTH_SHORT).show();
                    }
                }
                if (companySwitch.isChecked()) {
                    if (user.getCompany() != null && !user.getCompany().trim().isEmpty()) {
                        if (user.getCompanyVisibility() == 1) {
                            postDataParams.put(context.getString(R.string.oCompany), CODE_PUBLIC + user.getCompany());
                        } else {
                            postDataParams.put(context.getString(R.string.oCompany), CODE_PRIVATE + encryptBase64(publicKey, user.getCompany()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oCompanyNeeded), Toast.LENGTH_SHORT).show();
                    }
                }
                postDataParams.put(context.getString(R.string.oWage), CODE_PUBLIC + user.getWage());
                if (universitySwitch.isChecked()) {
                    if (user.getUniversity() != null && !user.getUniversity().trim().isEmpty()) {
                        if (user.getUniversityVisibility() == 1) {
                            postDataParams.put(context.getString(R.string.oUniversity), CODE_PUBLIC + user.getUniversity());
                        } else {
                            postDataParams.put(context.getString(R.string.oUniversity), CODE_PRIVATE + encryptBase64(publicKey, user.getUniversity()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oUniversityNeeded), Toast.LENGTH_SHORT).show();
                    }
                }
                if (careerSwitch.isChecked()) {
                    if (user.getCareer() != null && !user.getCareer().trim().isEmpty()) {
                        if (user.getCareerVisibility() == 1) {
                            postDataParams.put(context.getString(R.string.oCareer), CODE_PUBLIC + user.getCareer());
                        } else {
                            postDataParams.put(context.getString(R.string.oCareer), CODE_PRIVATE + encryptBase64(publicKey, user.getCareer()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oCareerNeeded), Toast.LENGTH_SHORT).show();
                    }
                }
                if (sector1Switch.isChecked()) {
                    if (user.getSector1() != null && !user.getSector1().trim().isEmpty()) {
                        if (user.getSector1Visibility() == 1) {
                            postDataParams.put(context.getString(R.string.oSector1), CODE_PUBLIC + user.getSector1());
                        } else {
                            postDataParams.put(context.getString(R.string.oSector1), CODE_PRIVATE + encryptBase64(publicKey, user.getSector1()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oSector1Needed), Toast.LENGTH_SHORT).show();
                    }
                }
                if (sector2Switch.isChecked()) {
                    if (user.getSector2() != null && !user.getSector2().trim().isEmpty()) {
                        if (user.getSector2Visibility() == 1) {
                            postDataParams.put(context.getString(R.string.oSector2), CODE_PUBLIC + user.getSector2());
                        } else {
                            postDataParams.put(context.getString(R.string.oSector2), CODE_PRIVATE + encryptBase64(publicKey, user.getSector2()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oSector2Needed), Toast.LENGTH_SHORT).show();
                    }
                }
                if (experienceSwitch.isChecked()) {
                    if (user.getExperience() != null && !user.getExperience().trim().isEmpty()) {
                        if (user.getExperienceVisibility() == 1) {
                            postDataParams.put(context.getString(R.string.oExperience), CODE_PUBLIC + user.getExperience());
                        } else {
                            postDataParams.put(context.getString(R.string.oExperience), CODE_PRIVATE + encryptBase64(publicKey, user.getExperience()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oExperienceNeeded), Toast.LENGTH_SHORT).show();
                    }
                }
                if (languagesSwitch.isChecked()) {
                    if (user.getLanguages() != null && !user.getLanguages().trim().isEmpty()) {
                        if (user.getLanguagesVisibility() == 1) {
                            postDataParams.put(context.getString(R.string.oLanguages), CODE_PUBLIC + user.getLanguages());
                        } else {
                            postDataParams.put(context.getString(R.string.oLanguages), CODE_PRIVATE + encryptBase64(publicKey, user.getLanguages()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oLanguagesNeeded), Toast.LENGTH_SHORT).show();
                    }
                }
                if (knowledgesSwitch.isChecked()) {
                    if (user.getKnowledges() != null && !user.getKnowledges().trim().isEmpty()) {
                        if (user.getKnowledgesVisibility() == 1) {
                            postDataParams.put(context.getString(R.string.oKnowledges), CODE_PUBLIC + user.getKnowledges());
                        } else {
                            postDataParams.put(context.getString(R.string.oKnowledges), CODE_PRIVATE + encryptBase64(publicKey, user.getKnowledges()));
                        }
                    } else {
                        errors++;
                        Toast.makeText(context, context.getString(R.string.oKnowledgesNeeded), Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                Log.e("error", "ERROR DE CIFRADO NegotiationACTIVITY " + e);
            }

            String name = "";
            if (contactNameSwitch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoName()))) {
                    name = negotiation.getoName();
                } else {
                    name = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rName), name);

            String surnames = "";
            if (contactSurnamesSwitch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoSurnames()))) {
                    surnames = negotiation.getoSurnames();
                } else {
                    surnames = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rSurnames), surnames);

            String email = "";
            if (contactEmailSwitch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoEmail()))) {
                    email = negotiation.getoEmail();
                } else {
                    email = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rEmail), email);

            String telephone = "";
            if (contactTelephoneSwitch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoTelephone()))) {
                    telephone = negotiation.getoTelephone();
                } else {
                    telephone = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rTelephone), telephone);

            String job = "";
            if (contactJobSwitch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoJob()))) {
                    job = negotiation.getoJob();
                } else {
                    job = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rJob), job);

            String company = "";
            if (contactCompanySwitch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoCompany()))) {
                    company = negotiation.getoCompany();
                } else {
                    company = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rCompany), company);

            postDataParams.put(context.getString(R.string.rWage), negotiation.getoWage());

            String university = "";
            if (contactUniversitySwitch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoUniversity()))) {
                    university = negotiation.getoUniversity();
                } else {
                    university = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rUniversity), university);

            String career = "";
            if (contactCareerSwitch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoCareer()))) {
                    career = negotiation.getoCareer();
                } else {
                    career = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rCareer), career);

            String sector1 = "";
            if (contactSector1Switch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoSector1()))) {
                    sector1 = negotiation.getoSector1();
                } else {
                    sector1 = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rSector1), sector1);

            String sector2 = "";
            if (contactSector2Switch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoSector2()))) {
                    sector2 = negotiation.getoSector2();
                } else {
                    sector2 = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rSector2), sector2);

            String experience = "";
            if (contactExperienceSwitch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoExperience()))) {
                    experience = negotiation.getoExperience();
                } else {
                    experience = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rExperience), experience);

            String languages = "";
            if (contactLanguagesSwitch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoLanguages()))) {
                    languages = negotiation.getoLanguages();
                } else {
                    languages = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rLanguages), languages);

            String knowledges = "";
            if (contactKnowledgesSwitch.isChecked()) {
                if (!"".equals(checkPrivacy(negotiation.getoKnowledges()))) {
                    knowledges = negotiation.getoKnowledges();
                } else {
                    knowledges = CODE_PRIVATE;
                }
            }
            postDataParams.put(context.getString(R.string.rKnowledges), knowledges);

            if (errors == 0) {
                if (isNetworkAvailable((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE)))) {
                    Thread tr = new Thread() {
                        @Override
                        public void run() {
                            String success = sendDataPOST(context, urlServer, UPDATE_NEGOTIATION, postDataParams);
                            if (String.valueOf(SUCCESS).equals(success)) {
                                final HashMap<String, String> postDataParams = new HashMap<>();
                                postDataParams.put(context.getString(R.string.user), user.getUser());
                                postDataParams.put(context.getString(R.string.state), REFUSED);
                                Intent sendIntent = new Intent(RenegotiateNegotiationActivity.this, NegotationListActivity.class);
                                success = sendDataPOST(context, urlServer, SEARCH_NEGOTIATIONS, postDataParams);
                                if (String.valueOf(SUCCESS).equals(success)) {
                                    startActivity(sendIntent);
                                } else {
                                    runOnUiThread(new Thread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(context, getString(R.string.renegotiateNegotiationError), Toast.LENGTH_LONG).show();
                                        }
                                    }));
                                }
                            } else {
                                runOnUiThread(new Thread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(context, getString(R.string.createNegotationError), Toast.LENGTH_LONG).show();
                                    }
                                }));
                            }
                        }
                    };
                    tr.start();
                    Toast.makeText(context, getString(R.string.negotiationSended), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(context, getString(R.string.internetRequired), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}