package pojos;
import javax.persistence.*;

@Entity
@Table(name="tags")
public class Tag extends BaseEntity{
	@Column(name="tag_name",length =30,unique = true)
	private String tagName;

	public Tag() {
		// TODO Auto-generated constructor stub
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "Tag ID "+getId()+" [tagName=" + tagName + "]";
	}
	
	

	
}
