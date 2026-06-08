package dev.nima.store.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @OneToMany(mappedBy = "user")
  @Builder.Default
  private List<Address> addresses = new ArrayList<>();

  public void addAddress(Address address) {
    addresses.add(address);
    address.setUser(this);
  }

  public void removeAddress(Address address) {
    addresses.remove(address);
    address.setUser(null);
  }

  public void addTag(String tag) {
    var newTag = new Tag(tag);
    tags.add(newTag);
    newTag.getUsers().add(this);
  }

  public void removeTag(String tag) {
    var tagToRemove = tags.stream()
        .filter(t -> t.getName().equals(tag))
        .findFirst()
        .orElse(null);
    if (tagToRemove != null) {
      tags.remove(tagToRemove);
      tagToRemove.getUsers().remove(this);
    }
  }

  @ManyToMany
  @JoinTable(name = "user_tags", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
  @Builder.Default
  private Set<Tag> tags = new HashSet<>();

  @OneToOne(mappedBy = "user")
  private Profile profile;

  // public User(Long id, String name, String email, String password) {
  // this.id = id;
  // this.name = name;
  // this.email = email;
  // this.password = password;
  // }
}
