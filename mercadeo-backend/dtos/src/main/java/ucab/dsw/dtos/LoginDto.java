package ucab.dsw.dtos;


public class LoginDto {
    private String password;
    private String email;

    public LoginDto(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public LoginDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
