package tim73.isa_2020.model;

// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenState {
	
    private String accessToken;
    private Long expiresIn;
    private String role;
    private String status;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.role = null;
        this.status = null;
    }

    public UserTokenState(String accessToken, long expiresIn, String role, String status) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.role = role;
        this.status = status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

	public String getRole() {
		return role;
	}

	public String getStatus() {
		return status;
	}

	
}