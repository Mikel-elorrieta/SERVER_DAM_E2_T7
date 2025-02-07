package modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ikastetxe implements java.io.Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5324437671510611081L;


	    @JsonProperty("CCEN")
	    private int CCEN;            // Centro

	    @JsonProperty("NOM")
	    private String NOM;          // Nombre

	    @JsonProperty("NOME")
	    private String NOME;         // Nombre extendido

	    @JsonProperty("DGENRC")
	    private String DGENRC;       // Descripción General RC

	    @JsonProperty("DGENRE")
	    private String DGENRE;       // Descripción General RE

	    @JsonProperty("GENR")
	    private String GENR;         // Código GENR

	    @JsonProperty("MUNI")
	    private int MUNI;            // Municipio

	    @JsonProperty("DMUNIC")
	    private String DMUNIC;       // Descripción Municipio

	    @JsonProperty("DMUNIE")
	    private String DMUNIE;       // Descripción Municipio extendida

	    @JsonProperty("DTERRC")
	    private String DTERRC;       // Territorio RC

	    @JsonProperty("DTERRE")
	    private String DTERRE;       // Territorio RE

	    @JsonProperty("DEPE")
	    private String DEPE;            // Departamento

	    @JsonProperty("DTITUC")
	    private String DTITUC;       // Título de educación

	    @JsonProperty("DTITUE")
	    private String DTITUE;       // Título de educación extendido

	    @JsonProperty("DOMI")
	    private String DOMI;         // Dirección

	    @JsonProperty("CPOS")
	    private int CPOS;            // Código postal

	    @JsonProperty("TEL1")
	    private long TEL1;           // Teléfono

	    @JsonProperty("TFAX")
	    private String TFAX;         // Fax

	    @JsonProperty("EMAIL")
	    private String EMAIL;        // Correo electrónico

	    @JsonProperty("PAGINA")
	    private String PAGINA;       // Página web

	    @JsonProperty("COOR_X")
	    private String COOR_X;       // Coordenada X

	    @JsonProperty("COOR_Y")
	    private String COOR_Y;       // Coordenada Y

	    @JsonProperty("LATITUD")
	    private double LATITUD;      // Latitud

	    @JsonProperty("LONGITUD")
	    private double LONGITUD;     // Longitud

	    // Getters y Setters
	    public int getCCEN() {
	        return CCEN;
	    }

	    public void setCCEN(int CCEN) {
	        this.CCEN = CCEN;
	    }

	    public String getNOM() {
	        return NOM;
	    }

	    public void setNOM(String NOM) {
	        this.NOM = NOM;
	    }

	    public String getNOME() {
	        return NOME;
	    }

	    public void setNOME(String NOME) {
	        this.NOME = NOME;
	    }

	    public String getDGENRC() {
	        return DGENRC;
	    }

	    public void setDGENRC(String DGENRC) {
	        this.DGENRC = DGENRC;
	    }

	    public String getDGENRE() {
	        return DGENRE;
	    }

	    public void setDGENRE(String DGENRE) {
	        this.DGENRE = DGENRE;
	    }

	    public String getGENR() {
	        return GENR;
	    }

	    public void setGENR(String GENR) {
	        this.GENR = GENR;
	    }

	    public int getMUNI() {
	        return MUNI;
	    }

	    public void setMUNI(int MUNI) {
	        this.MUNI = MUNI;
	    }

	    public String getDMUNIC() {
	        return DMUNIC;
	    }

	    public void setDMUNIC(String DMUNIC) {
	        this.DMUNIC = DMUNIC;
	    }

	    public String getDMUNIE() {
	        return DMUNIE;
	    }

	    public void setDMUNIE(String DMUNIE) {
	        this.DMUNIE = DMUNIE;
	    }

	    public String getDTERRC() {
	        return DTERRC;
	    }

	    public void setDTERRC(String DTERRC) {
	        this.DTERRC = DTERRC;
	    }

	    public String getDTERRE() {
	        return DTERRE;
	    }

	    public void setDTERRE(String DTERRE) {
	        this.DTERRE = DTERRE;
	    }

	    public String getDEPE() {
	        return DEPE;
	    }

	    public void setDEPE(String DEPE) {
	        this.DEPE = DEPE;
	    }

	    public String getDTITUC() {
	        return DTITUC;
	    }

	    public void setDTITUC(String DTITUC) {
	        this.DTITUC = DTITUC;
	    }

	    public String getDTITUE() {
	        return DTITUE;
	    }

	    public void setDTITUE(String DTITUE) {
	        this.DTITUE = DTITUE;
	    }

	    public String getDOMI() {
	        return DOMI;
	    }

	    public void setDOMI(String DOMI) {
	        this.DOMI = DOMI;
	    }

	    public int getCPOS() {
	        return CPOS;
	    }

	    public void setCPOS(int CPOS) {
	        this.CPOS = CPOS;
	    }

	    public long getTEL1() {
	        return TEL1;
	    }

	    public void setTEL1(long TEL1) {
	        this.TEL1 = TEL1;
	    }

	    public String getTFAX() {
	        return TFAX;
	    }

	    public void setTFAX(String TFAX) {
	        this.TFAX = TFAX;
	    }

	    public String getEMAIL() {
	        return EMAIL;
	    }

	    public void setEMAIL(String EMAIL) {
	        this.EMAIL = EMAIL;
	    }

	    public String getPAGINA() {
	        return PAGINA;
	    }

	    public void setPAGINA(String PAGINA) {
	        this.PAGINA = PAGINA;
	    }

	    public String getCOOR_X() {
	        return COOR_X;
	    }

	    public void setCOOR_X(String COOR_X) {
	        this.COOR_X = COOR_X;
	    }

	    public String getCOOR_Y() {
	        return COOR_Y;
	    }

	    public void setCOOR_Y(String COOR_Y) {
	        this.COOR_Y = COOR_Y;
	    }

	    public double getLATITUD() {
	        return LATITUD;
	    }

	    public void setLATITUD(double LATITUD) {
	        this.LATITUD = LATITUD;
	    }

	    public double getLONGITUD() {
	        return LONGITUD;
	    }

	    public void setLONGITUD(double LONGITUD) {
	        this.LONGITUD = LONGITUD;
	    }
	}
