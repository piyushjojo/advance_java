package pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@Column(name = "role_name", length = 20)
	private UserRole roleName;
	public Role() {
		// TODO Auto-generated constructor stub
	}
	public Role(UserRole roleName) {
		super();
		this.roleName = roleName;
	}
	public UserRole getRoleName() {
		return roleName;
	}
	public void setRoleName(UserRole roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "Role ID "+getId()+" [roleName=" + roleName + "]";
	}
}
