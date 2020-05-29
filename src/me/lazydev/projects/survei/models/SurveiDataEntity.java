package me.lazydev.projects.survei.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "survei_data", schema = "survei")
public class SurveiDataEntity {
    private int id;
    private String namaDepan;
    private String namaBelakang;
    private String alamat;
    private String kotaKab;
    private String negaraBagian;
    private int kodePos;
    private String email;
    private String nomerTelepon;
    private Date tanggalSurvei;
    private String checkboxInput;
    private String radioInput;
    private String dropdownInput;
    private String commentInput;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nama_depan", nullable = false, length = 255)
    public String getNamaDepan() {
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    @Basic
    @Column(name = "nama_belakang", nullable = false, length = 255)
    public String getNamaBelakang() {
        return namaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    @Basic
    @Column(name = "alamat", nullable = false, length = -1)
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Basic
    @Column(name = "kota_kab", nullable = false, length = 255)
    public String getKotaKab() {
        return kotaKab;
    }

    public void setKotaKab(String kotaKab) {
        this.kotaKab = kotaKab;
    }

    @Basic
    @Column(name = "negara_bagian", nullable = false, length = 255)
    public String getNegaraBagian() {
        return negaraBagian;
    }

    public void setNegaraBagian(String negaraBagian) {
        this.negaraBagian = negaraBagian;
    }

    @Basic
    @Column(name = "kode_pos", nullable = false)
    public int getKodePos() {
        return kodePos;
    }

    public void setKodePos(int kodePos) {
        this.kodePos = kodePos;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "nomer_telepon", nullable = false, length = 18)
    public String getNomerTelepon() {
        return nomerTelepon;
    }

    public void setNomerTelepon(String nomerTelepon) {
        this.nomerTelepon = nomerTelepon;
    }

    @Basic
    @Column(name = "tanggal_survei", nullable = false)
    public Date getTanggalSurvei() {
        return tanggalSurvei;
    }

    public void setTanggalSurvei(Date tanggalSurvei) {
        this.tanggalSurvei = tanggalSurvei;
    }

    @Basic
    @Column(name = "checkbox_input", nullable = false, length = -1)
    public String getCheckboxInput() {
        return checkboxInput;
    }

    public void setCheckboxInput(String checkboxInput) {
        this.checkboxInput = checkboxInput;
    }

    @Basic
    @Column(name = "radio_input", nullable = false, length = 255)
    public String getRadioInput() {
        return radioInput;
    }

    public void setRadioInput(String radioInput) {
        this.radioInput = radioInput;
    }

    @Basic
    @Column(name = "dropdown_input", nullable = false, length = 255)
    public String getDropdownInput() {
        return dropdownInput;
    }

    public void setDropdownInput(String dropdownInput) {
        this.dropdownInput = dropdownInput;
    }

    @Basic
    @Column(name = "comment_input", nullable = false, length = -1)
    public String getCommentInput() {
        return commentInput;
    }

    public void setCommentInput(String commentInput) {
        this.commentInput = commentInput;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurveiDataEntity that = (SurveiDataEntity) o;
        return id == that.id &&
                kodePos == that.kodePos &&
                Objects.equals(namaDepan, that.namaDepan) &&
                Objects.equals(namaBelakang, that.namaBelakang) &&
                Objects.equals(alamat, that.alamat) &&
                Objects.equals(kotaKab, that.kotaKab) &&
                Objects.equals(negaraBagian, that.negaraBagian) &&
                Objects.equals(email, that.email) &&
                Objects.equals(nomerTelepon, that.nomerTelepon) &&
                Objects.equals(tanggalSurvei, that.tanggalSurvei) &&
                Objects.equals(checkboxInput, that.checkboxInput) &&
                Objects.equals(radioInput, that.radioInput) &&
                Objects.equals(dropdownInput, that.dropdownInput) &&
                Objects.equals(commentInput, that.commentInput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namaDepan, namaBelakang, alamat, kotaKab, negaraBagian, kodePos, email, nomerTelepon, tanggalSurvei, checkboxInput, radioInput, dropdownInput, commentInput);
    }
}
