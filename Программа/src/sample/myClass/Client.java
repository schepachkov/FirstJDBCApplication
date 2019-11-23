package sample.myClass;

public class Client {
    private String FIO;
    private String telephone;
    private String adress;

    public Client(String FIO, String telephone, String adress) {
        this.FIO = FIO;
        this.telephone = telephone;
        this.adress = adress;
    }

    public Client(String clientName) {
        FIO = clientName;
    }

    public Client(String clientName, String clientTelephone) {
        FIO = clientName;
        telephone = clientTelephone;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
