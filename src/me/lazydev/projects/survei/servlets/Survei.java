package me.lazydev.projects.survei.servlets;

import com.google.gson.Gson;
import me.lazydev.projects.survei.helpers.DateUtils;
import me.lazydev.projects.survei.models.SurveiDataEntity;

import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

// These links might help in near future
// https://github.com/Murmur/ScopedEntityManager/blob/master/example_webapp/build.xml
// https://forum.hibernate.org/viewtopic.php?f=1&t=1044001
// https://docs.appdynamics.com/display/PRO45/Apache+Tomcat+Startup+Settings
// https://intellij-support.jetbrains.com/hc/en-us/community/posts/360000112404-Openjpa-Enhancement-Support
// https://stackoverflow.com/questions/22658185/java-lang-classnotfoundexception-javax-faces-webapp-facesservlet
// https://stackoverflow.com/questions/15113628/java-lang-classnotfoundexception-javax-servlet-jsp-jstl-core-config
@ManagedBean
public class Survei {
    //private EntityManager entityManager;
    private final String PERSISTENCE_UNIT_NAME = "LocalPersistence";
    private SurveiDataEntity surveiData;
    private List<SurveiDataEntity> surveiDataEntities;

    private String namaDepan, namaBelakang, alamat, kotaKab, provinsiNeBag, email;
    private Date tanggalSurvei;

    private String radioButtonInput, dropdownInput, commentInput;
    private String[] checkBoxInput;

    private String kodePos, nomerTelepon;
    private boolean isFormFilled = false;

    public List<SurveiDataEntity> getSurveiDataEntities() {
        return surveiDataEntities;
    }

    public void setSurveiDataEntities(List<SurveiDataEntity> surveiDataEntities) {
        this.surveiDataEntities = surveiDataEntities;
    }

    public SurveiDataEntity getSurveiData() {
        return surveiData;
    }

    public void setSurveiData(SurveiDataEntity surveiData) {
        this.surveiData = surveiData;
    }

    public boolean getIsFormFilled() {
        return isFormFilled;
    }

    public void setIsFormFilled(boolean formFilled) {
        this.isFormFilled = formFilled;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getNomerTelepon() {
        return nomerTelepon;
    }

    public void setNomerTelepon(String nomerTelepon) {
        this.nomerTelepon = nomerTelepon;
    }

    public String[] getCheckBoxInput() {
        return checkBoxInput;
    }

    public String getRadioButtonInput() {
        return radioButtonInput;
    }

    public String getDropdownInput() {
        return dropdownInput;
    }

    public String getCommentInput() {
        return commentInput == null ? "Tidak ada tambahan" : commentInput;
    }

    public void setCheckBoxInput(String[] checkBoxInput) {
        this.checkBoxInput = checkBoxInput;
    }

    public void setRadioButtonInput(String radioButtonInput) {
        this.radioButtonInput = radioButtonInput;
    }

    public void setDropdownInput(String dropdownInput) {
        this.dropdownInput = dropdownInput;
    }

    public void setCommentInput(String commentInput) {
        this.commentInput = commentInput;
    }
    ///////////////////////////////////////////////////////
    public Date getTanggalSurvei() {
        if (tanggalSurvei == null) {
            tanggalSurvei = new Date();
        }
        return tanggalSurvei;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKotaKab() {
        return kotaKab;
    }

    public String getProvinsiNeBag() {
        return provinsiNeBag;
    }

    public String getEmail() {
        return email;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setKotaKab(String kotaKab) {
        this.kotaKab = kotaKab;
    }

    public void setProvinsiNeBag(String provinsiNeBag) {
        this.provinsiNeBag = provinsiNeBag;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTanggalSurvei(Date tanggalSurvei) {
        this.tanggalSurvei = tanggalSurvei;
    }
    /////////////////////////////////////////////////////

    // Validasi Tanggal
    public void validateDate(FacesContext context, UIComponent comp, Object value) {
        System.out.println("inside validate method");
        Date theDate = (Date) value;

        if (!DateUtils.isSameDay(new Date(), theDate)) {
            ((UIInput) comp).setValid(false);
            FacesMessage message = new FacesMessage(
                    "Tanggal harus tanggal hari ini");
            context.addMessage(comp.getClientId(context), message);
        }

    }

    private int saveData() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(this.PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        SurveiDataEntity surveiDataEntity = new SurveiDataEntity();
        surveiDataEntity.setNamaDepan(this.namaDepan);
        surveiDataEntity.setNamaBelakang(this.namaBelakang);
        surveiDataEntity.setAlamat(this.alamat);
        surveiDataEntity.setKotaKab(this.kotaKab);
        surveiDataEntity.setNegaraBagian(this.provinsiNeBag);
        surveiDataEntity.setKodePos(Integer.parseInt(this.kodePos));
        surveiDataEntity.setEmail(this.email);
        surveiDataEntity.setNomerTelepon(this.nomerTelepon);
        surveiDataEntity.setTanggalSurvei(new java.sql.Date(this.tanggalSurvei.getTime()));
        surveiDataEntity.setCheckboxInput(new Gson().toJson(this.checkBoxInput));
        surveiDataEntity.setRadioInput(this.radioButtonInput);
        surveiDataEntity.setDropdownInput(this.dropdownInput);
        surveiDataEntity.setCommentInput(this.commentInput);
        em.persist(surveiDataEntity);
        // I literally don't know how this even work
        em.flush();
        em.getTransaction().commit();
        em.close();
        return surveiDataEntity.getId();
    }

    private List<SurveiDataEntity> getAllData() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(this.PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s from SurveiDataEntity s");
        @SuppressWarnings({"unchecked"})
        List<SurveiDataEntity> surveiDataEntities = (List<SurveiDataEntity>) q.getResultList();
        //SurveiDataEntity surveiDataEntity = (SurveiDataEntity) q.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return surveiDataEntities;
        //return surveiDataEntity;
    }

    private SurveiDataEntity getData(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(this.PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT s from SurveiDataEntity s WHERE s.id = :id");
        q.setParameter("id", id);
        SurveiDataEntity surveiDataEntity = (SurveiDataEntity) q.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return surveiDataEntity;
    }

    public String submitData() {
        int x = this.saveData();
        this.isFormFilled = true;
        this.surveiData = this.getData(x);
        return("form-result");
    }

    public String lookOtherResults() {
        this.surveiDataEntities = getAllData();
        return("form-result-list");
    }

    public void listener() {
        this.surveiDataEntities = getAllData();
    }

}

