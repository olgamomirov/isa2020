package tim73.isa_2020.token;

// DTO za login
public class JwtAuthenticationRequest {
	
    private String email;
    private String lozinka;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String email, String lozinka) {
        this.setEmail(email);
        this.setLozinka(lozinka);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return this.lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
