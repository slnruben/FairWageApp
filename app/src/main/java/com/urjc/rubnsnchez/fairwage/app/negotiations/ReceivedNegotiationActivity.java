package com.urjc.rubnsnchez.fairwage.app.negotiations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.app.MyApplication;
import com.urjc.rubnsnchez.fairwage.app.common.BBDDHelper;
import com.urjc.rubnsnchez.fairwage.app.contacts.Contact;
import com.urjc.rubnsnchez.fairwage.app.contacts.SearchActivity;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Objects;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.ACCEPTED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.ACCEPT_NEGOTIATION;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.CODE_PRIVATE;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.CODE_PUBLIC;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.DELETE_NEGOTIATION;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.GET_CONTACT_PUBLIC_KEY;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.NEGOTIATION;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REFUSED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REFUSE_NEGOTIATION;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SEARCH_NEGOTIATIONS;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SUCCESS;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.getUserData;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.tableRowCount;
import static com.urjc.rubnsnchez.fairwage.app.common.CipherUtil.encryptBase64;
import static com.urjc.rubnsnchez.fairwage.app.common.CipherUtil.stringToPublicKey;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.createFullNegotiationBundle;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.isNetworkAvailable;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.sendDataPOST;

public class ReceivedNegotiationActivity extends Activity {
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
    PublicKey contactPublicKey;
    String publicKeyString;

    private void setSwitches() {
        if (user.getUser().equals(negotiation.getUserCreator())) {
            if (!negotiation.getoName().isEmpty()) {
                nameSwitch.setChecked(true);
            }
            if (!negotiation.getoSurnames().isEmpty()) {
                surnamesSwitch.setChecked(true);
            }
            if (!negotiation.getoJob().isEmpty()) {
                jobSwitch.setChecked(true);
            }
            if (!negotiation.getoCompany().isEmpty()) {
                companySwitch.setChecked(true);
            }
            if (!negotiation.getoEmail().isEmpty()) {
                emailSwitch.setChecked(true);
            }
            if (!negotiation.getoTelephone().isEmpty()) {
                telephoneSwitch.setChecked(true);
            }
            if (!negotiation.getoUniversity().isEmpty()) {
                universitySwitch.setChecked(true);
            }
            if (!negotiation.getoCareer().isEmpty()) {
                careerSwitch.setChecked(true);
            }
            if (!negotiation.getoSector1().isEmpty()) {
                sector1Switch.setChecked(true);
            }
            if (!negotiation.getoSector2().isEmpty()) {
                sector2Switch.setChecked(true);
            }

            if (!negotiation.getoExperience().isEmpty()) {
                experienceSwitch.setChecked(true);
            }
            if (!negotiation.getoLanguages().isEmpty()) {
                languagesSwitch.setChecked(true);
            }
            if (!negotiation.getoKnowledges().isEmpty()) {
                knowledgesSwitch.setChecked(true);
            }
            if (!negotiation.getrName().isEmpty()) {
                contactNameSwitch.setChecked(true);
            }
            if (!negotiation.getrSurnames().isEmpty()) {
                contactSurnamesSwitch.setChecked(true);
            }
            if (!negotiation.getrJob().isEmpty()) {
                contactJobSwitch.setChecked(true);
            }
            if (!negotiation.getrCompany().isEmpty()) {
                contactCompanySwitch.setChecked(true);
            }
            if (!negotiation.getrEmail().isEmpty()) {
                contactEmailSwitch.setChecked(true);
            }
            if (!negotiation.getrTelephone().isEmpty()) {
                contactTelephoneSwitch.setChecked(true);
            }
            if (!negotiation.getrUniversity().isEmpty()) {
                contactUniversitySwitch.setChecked(true);
            }
            if (!negotiation.getrCareer().isEmpty()) {
                contactCareerSwitch.setChecked(true);
            }
            if (!negotiation.getrSector1().isEmpty()) {
                contactSector1Switch.setChecked(true);
            }
            if (!negotiation.getrSector2().isEmpty()) {
                contactSector2Switch.setChecked(true);
            }
            if (!negotiation.getrExperience().isEmpty()) {
                contactExperienceSwitch.setChecked(true);
            }
            if (!negotiation.getrLanguages().isEmpty()) {
                contactLanguagesSwitch.setChecked(true);
            }
            if (!negotiation.getrKnowledges().isEmpty()) {
                contactKnowledgesSwitch.setChecked(true);
            }
        } else {
            if (!negotiation.getrName().isEmpty()) {
                nameSwitch.setChecked(true);
            }
            if (!negotiation.getrSurnames().isEmpty()) {
                surnamesSwitch.setChecked(true);
            }
            if (!negotiation.getrJob().isEmpty()) {
                jobSwitch.setChecked(true);
            }
            if (!negotiation.getrCompany().isEmpty()) {
                companySwitch.setChecked(true);
            }
            if (!negotiation.getrEmail().isEmpty()) {
                emailSwitch.setChecked(true);
            }
            if (!negotiation.getrTelephone().isEmpty()) {
                telephoneSwitch.setChecked(true);
            }
            if (!negotiation.getrUniversity().isEmpty()) {
                universitySwitch.setChecked(true);
            }
            if (!negotiation.getrCareer().isEmpty()) {
                careerSwitch.setChecked(true);
            }
            if (!negotiation.getrSector1().isEmpty()) {
                sector1Switch.setChecked(true);
            }
            if (!negotiation.getrSector2().isEmpty()) {
                sector2Switch.setChecked(true);
            }

            if (!negotiation.getrExperience().isEmpty()) {
                experienceSwitch.setChecked(true);
            }
            if (!negotiation.getrLanguages().isEmpty()) {
                languagesSwitch.setChecked(true);
            }
            if (!negotiation.getrKnowledges().isEmpty()) {
                knowledgesSwitch.setChecked(true);
            }
            if (!negotiation.getoName().isEmpty()) {
                contactNameSwitch.setChecked(true);
            }
            if (!negotiation.getoSurnames().isEmpty()) {
                contactSurnamesSwitch.setChecked(true);
            }
            if (!negotiation.getoJob().isEmpty()) {
                contactJobSwitch.setChecked(true);
            }
            if (!negotiation.getoCompany().isEmpty()) {
                contactCompanySwitch.setChecked(true);
            }
            if (!negotiation.getoEmail().isEmpty()) {
                contactEmailSwitch.setChecked(true);
            }
            if (!negotiation.getoTelephone().isEmpty()) {
                contactTelephoneSwitch.setChecked(true);
            }
            if (!negotiation.getoUniversity().isEmpty()) {
                contactUniversitySwitch.setChecked(true);
            }
            if (!negotiation.getoCareer().isEmpty()) {
                contactCareerSwitch.setChecked(true);

            }
            if (!negotiation.getoSector1().isEmpty()) {
                contactSector1Switch.setChecked(true);
            }
            if (!negotiation.getoSector2().isEmpty()) {
                contactSector2Switch.setChecked(true);
            }
            if (!negotiation.getoExperience().isEmpty()) {
                contactExperienceSwitch.setChecked(true);
            }
            if (!negotiation.getoLanguages().isEmpty()) {
                contactLanguagesSwitch.setChecked(true);
            }
            if (!negotiation.getoKnowledges().isEmpty()) {
                contactKnowledgesSwitch.setChecked(true);
            }
        }
    }

    private void retrieveContactPublicKey() {
        final Context context = getApplicationContext();
        final String urlServer = ((MyApplication) context).getUrlServer();
        final HashMap<String, String> postDataParams = new HashMap<>();
        if (user.getUser().equals(negotiation.getUserCreator())) {
            postDataParams.put(context.getString(R.string.user), negotiation.getUserReceptor());
        } else {
            postDataParams.put(context.getString(R.string.user), negotiation.getUserCreator());
        }
        if (isNetworkAvailable((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE)))) {
            Thread tr = new Thread() {
                @Override
                public void run() {
                    publicKeyString = sendDataPOST(context, urlServer, GET_CONTACT_PUBLIC_KEY, postDataParams);
                    if (publicKeyString != null && !publicKeyString.trim().isEmpty()) {
                        contactPublicKey = stringToPublicKey(publicKeyString);
                    } else {
                        runOnUiThread(new Thread(new Runnable() {
                            public void run() {
                                Toast.makeText(context, getString(R.string.acceptedNegotationError), Toast.LENGTH_LONG).show();
                            }
                        }));
                    }
                }
            };
            tr.start();
        } else {
            Toast.makeText(context, getString(R.string.internetRequired), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.received_contact_negotiation);
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
        Button acceptButton = findViewById(R.id.sendButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button refuseButton = findViewById(R.id.refuseButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        Button renegotiateButton = findViewById(R.id.renegotiateButton);
        final Context context = getApplicationContext();
        String myUser = ((MyApplication) context).getUser();
        user = getUserData(context, myUser, NEGOTIATION);
        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        if (info != null) {
            negotiation = new Negotiation(info);
            setSwitches();
            retrieveContactPublicKey();
        }
        if (user.getUser().equals(negotiation.getUserCreator())) {

            //LinearLayout.LayoutParams pDelete = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
            //LinearLayout.LayoutParams pCancel = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            //pDelete.weight = (float) 1.5;
            //pDelete.gravity = Gravity.CENTER_VERTICAL;
            //pCancel.weight = (float) 2;
            //pCancel.gravity = Gravity.CENTER_VERTICAL;

            acceptButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.VISIBLE);
            //deleteButton.setLayoutParams(pDelete);
            refuseButton.setVisibility(View.GONE);
            renegotiateButton.setVisibility(View.GONE);
            //cancelButton.setLayoutParams(pCancel);
        }
        acceptButton.setOnClickListener(new ReceivedNegotiationActivity.acceptButtonListener());
        deleteButton.setOnClickListener(new ReceivedNegotiationActivity.deleteButtonListener());
        refuseButton.setOnClickListener(new ReceivedNegotiationActivity.refuseButtonListener());
        renegotiateButton.setOnClickListener(new ReceivedNegotiationActivity.renegotiateButtonListener());
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Comprueba si el usuario introducido en los campos concuerda con los datos de la BBDD del servidor,
     * si es correcto permite el acceso a la aplicacion y guarda el usuario para futuras sesiones.
     */
    private class acceptButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int errors = 0;
            final Context context = getApplicationContext();
            final String urlServer = ((MyApplication) context).getUrlServer();
            final HashMap<String, String> postDataParams = new HashMap<>();
            postDataParams.put(context.getString(R.string.id), String.valueOf(negotiation.getIdServer()));
            postDataParams.put(context.getString(R.string.state), ACCEPTED);
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
            if (nameSwitch.isChecked()) {
                if (user.getName() != null && !user.getName().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rName), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getName()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rNameNeeded), Toast.LENGTH_SHORT).show();
                }
            }
            if (surnamesSwitch.isChecked()) {
                if (user.getSurnames() != null && !user.getSurnames().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rSurnames), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getSurnames()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rSurnamesNeeded), Toast.LENGTH_SHORT).show();
                }
            }
            if (emailSwitch.isChecked()) {
                if (user.getEmail() != null && !user.getEmail().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rEmail), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getEmail()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rEmailNeeded), Toast.LENGTH_SHORT).show();
                }
            }
            if (telephoneSwitch.isChecked()) {
                if (user.getTelephone() != null && !user.getTelephone().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rTelephone), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getTelephone()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rTelephoneNeeded), Toast.LENGTH_SHORT).show();
                }
            }
            if (jobSwitch.isChecked()) {
                if (user.getJob() != null && !user.getJob().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rJob), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getJob()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rJobNeeded), Toast.LENGTH_SHORT).show();
                }
            }
            if (companySwitch.isChecked()) {
                if (user.getCompany() != null && !user.getCompany().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rCompany), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getCompany()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rCompanyNeeded), Toast.LENGTH_SHORT).show();
                }
            }
            postDataParams.put(context.getString(R.string.rWage), CODE_PUBLIC + user.getWage());
            if (universitySwitch.isChecked()) {
                if (user.getUniversity() != null && !user.getUniversity().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rUniversity), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getUniversity()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rUniversityNeeded), Toast.LENGTH_SHORT).show();
                }
            }
            if (careerSwitch.isChecked()) {
                if (user.getCareer() != null && !user.getCareer().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rCareer), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getCareer()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rCareerNeeded), Toast.LENGTH_SHORT).show();
                }
            }
            if (sector1Switch.isChecked()) {
                if (user.getSector1() != null && !user.getSector1().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rSector1), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getSector1()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rSector1Needed), Toast.LENGTH_SHORT).show();
                }
            }
            if (sector2Switch.isChecked()) {
                if (user.getSector2() != null && !user.getSector2().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rSector2), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getSector2()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rSector2Needed), Toast.LENGTH_SHORT).show();
                }
            }
            if (experienceSwitch.isChecked()) {
                if (user.getExperience() != null && !user.getExperience().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rExperience), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getExperience()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rExperienceNeeded), Toast.LENGTH_SHORT).show();
                }
            }
            if (languagesSwitch.isChecked()) {
                if (user.getLanguages() != null && !user.getLanguages().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rLanguages), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getLanguages()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rLanguagesNeeded), Toast.LENGTH_SHORT).show();
                }
            }
            if (knowledgesSwitch.isChecked()) {
                if (user.getKnowledges() != null && !user.getKnowledges().trim().isEmpty()) {
                    postDataParams.put(context.getString(R.string.rKnowledges), CODE_PRIVATE + encryptBase64(contactPublicKey, user.getKnowledges()));
                } else {
                    errors++;
                    Toast.makeText(context, context.getString(R.string.rKnowledgesNeeded), Toast.LENGTH_SHORT).show();
                }
            }

            if (errors == 0) {
                if (isNetworkAvailable((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE)))) {
                    Thread tr = new Thread() {
                        @Override
                        public void run() {
                            String success = sendDataPOST(context, urlServer, ACCEPT_NEGOTIATION, postDataParams);
                            if (String.valueOf(SUCCESS).equals(success)) {
                                Intent intent = new Intent(ReceivedNegotiationActivity.this, AcceptedContactProfileActivity.class);
                                Bundle bundle = createFullNegotiationBundle(negotiation);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                runOnUiThread(new Thread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(context, getString(R.string.negotationAccepted), Toast.LENGTH_LONG).show();
                                    }
                                }));
                            } else {
                                runOnUiThread(new Thread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(context, getString(R.string.acceptedNegotationError), Toast.LENGTH_LONG).show();
                                    }
                                }));
                            }
                        }
                    };
                    tr.start();
                } else {
                    Toast.makeText(context, getString(R.string.internetRequired), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private class refuseButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final Context context = getApplicationContext();
            final String urlServer = ((MyApplication) context).getUrlServer();
            final HashMap<String, String> postDataParams = new HashMap<>();
            postDataParams.put(context.getString(R.string.id), String.valueOf(negotiation.getIdServer()));
            postDataParams.put(context.getString(R.string.state), REFUSED);
            if (isNetworkAvailable((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE)))) {
                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        String success = sendDataPOST(context, urlServer, REFUSE_NEGOTIATION, postDataParams);
                        if (String.valueOf(SUCCESS).equals(success)) {
                            runOnUiThread(new Thread(new Runnable() {
                                public void run() {
                                    Toast.makeText(context, getString(R.string.negotiationDeleted), Toast.LENGTH_SHORT).show();
                                }
                            }));

                            final HashMap<String, String> postDataParams = new HashMap<>();
                            postDataParams.put(context.getString(R.string.user), user.getUser());
                            postDataParams.put(context.getString(R.string.state), REFUSED);

                            success = sendDataPOST(context, urlServer, SEARCH_NEGOTIATIONS, postDataParams);
                            if (String.valueOf(SUCCESS).equals(success)) {
                                final int numRows = tableRowCount(context, BBDDHelper.DataSearchNegotation.TABLE_NAME);
                                if (numRows > 0) {
                                    Intent sendIntent = new Intent(ReceivedNegotiationActivity.this, NegotationListActivity.class);
                                    startActivity(sendIntent);
                                } else {
                                    runOnUiThread(new Thread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(context, getString(R.string.noNegotiations), Toast.LENGTH_LONG).show();
                                        }
                                    }));
                                    Intent sendIntent = new Intent(ReceivedNegotiationActivity.this, SearchActivity.class);
                                    startActivity(sendIntent);
                                }
                            } else {
                                runOnUiThread(new Thread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(context, getString(R.string.searchNegotationsError), Toast.LENGTH_LONG).show();
                                    }
                                }));
                            }
                        } else {
                            runOnUiThread(new Thread(new Runnable() {
                                public void run() {
                                    Toast.makeText(context, getString(R.string.deleteNegotiationError), Toast.LENGTH_LONG).show();
                                }
                            }));
                        }
                    }
                };
                tr.start();
            } else {
                Toast.makeText(context, getString(R.string.internetRequired), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class deleteButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final Context context = getApplicationContext();
            final String urlServer = ((MyApplication) context).getUrlServer();
            final HashMap<String, String> postDataParams = new HashMap<>();
            postDataParams.put(context.getString(R.string.id), String.valueOf(negotiation.getIdServer()));
            if (isNetworkAvailable((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE)))) {
                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        String success = sendDataPOST(context, urlServer, DELETE_NEGOTIATION, postDataParams);
                        if (String.valueOf(SUCCESS).equals(success)) {
                            final HashMap<String, String> postDataParams = new HashMap<>();
                            postDataParams.put(context.getString(R.string.user), user.getUser());
                            postDataParams.put(context.getString(R.string.state), REFUSED);
                            Intent sendIntent = new Intent(ReceivedNegotiationActivity.this, NegotationListActivity.class);
                            success = sendDataPOST(context, urlServer, SEARCH_NEGOTIATIONS, postDataParams);
                            if (String.valueOf(SUCCESS).equals(success)) {
                                startActivity(sendIntent);
                            } else {
                                runOnUiThread(new Thread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(context, getString(R.string.searchNegotationsError), Toast.LENGTH_LONG).show();
                                    }
                                }));
                            }
                        } else {
                            runOnUiThread(new Thread(new Runnable() {
                                public void run() {
                                    Toast.makeText(context, getString(R.string.deleteNegotiationError), Toast.LENGTH_LONG).show();
                                }
                            }));
                        }
                    }
                };
                tr.start();
                Toast.makeText(context, getString(R.string.negotiationDeleted), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, getString(R.string.internetRequired), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class renegotiateButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ReceivedNegotiationActivity.this, RenegotiateNegotiationActivity.class);
            Bundle bundle = createFullNegotiationBundle(negotiation);
            bundle.putString(String.valueOf(R.string.publicKey), publicKeyString);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
