package dev.nima.store.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a descriptive tag that can be assigned to users.
 * Has a Many-to-Many relationship with User entities.
 */
@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tags")
public class Tag {
  /**
   * Unique identifier for the tag.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  /**
   * Descriptive name of the tag (e.g., "VIP", "New User").
   */
  @Column(name = "name")
  private String name;

  /**
   * Set of users who have been assigned this tag.
   * Managed via the user_tags join table.
   */
  @ManyToMany(mappedBy = "tags")
  @ToString.Exclude
  private Set<User> users = new HashSet<>();

  /**
   * Convenient constructor to create a tag by name.
   */
  public Tag(String name) {
    this.name = name;
  }

}
