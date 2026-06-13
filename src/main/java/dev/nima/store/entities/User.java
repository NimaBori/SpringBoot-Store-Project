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

/**
 * Represents a registered user in the store.
 * Acts as a central entity with various relationships (Address, Tag, Profile).
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "users")
public class User {
  /**
   * Unique identifier for the user.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  /**
   * Full name of the user.
   */
  @Column(name = "name")
  private String name;

  /**
   * Unique email address used for login and notifications.
   */
  @Column(name = "email")
  private String email;

  /**
   * Encrypted password for user authentication.
   */
  @Column(name = "password")
  private String password;

  /**
   * List of addresses associated with this user.
   */
  @OneToMany(mappedBy = "user")
  @Builder.Default
  @ToString.Exclude
  private List<Address> addresses = new ArrayList<>();

  /**
   * Helper method to add an address and maintain bidirectionality.
   */
  public void addAddress(Address address) {
    addresses.add(address);
    address.setUser(this);
  }

  /**
   * Helper method to remove an address.
   */
  public void removeAddress(Address address) {
    addresses.remove(address);
    address.setUser(null);
  }

  /**
   * Helper method to add a tag by name.
   */
  public void addTag(String tag) {
    var newTag = new Tag(tag);
    tags.add(newTag);
    newTag.getUsers().add(this);
  }

  /**
   * Helper method to remove a tag by name.
   */
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

  /**
   * Set of tags assigned to this user.
   * Managed via the user_tags join table.
   */
  @ManyToMany
  @JoinTable(name = "user_tags", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
  @Builder.Default
  @ToString.Exclude
  private Set<Tag> tags = new HashSet<>();

  /**
   * Set of entries in this user's wishlist.
   * Managed via the Wishlist entity.
   */
  @OneToMany(mappedBy = "user", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  @ToString.Exclude
  private Set<Wishlist> wishlist = new HashSet<>();

  /**
   * Helper method to add a product to the wishlist via the Wishlist entity.
   */
  public void addToWishlist(Product product) {
    Wishlist wishlistItem = Wishlist.builder()
        .user(this)
        .product(product)
        .build();
    wishlist.add(wishlistItem);
  }

  /**
   * Helper method to remove a product from the wishlist.
   */
  public void removeFromWishlist(Product product) {
    wishlist.removeIf(item -> item.getProduct().equals(product));
  }

  /**
   * Extended profile information for the user.
   */
  @OneToOne(mappedBy = "user")
  @ToString.Exclude
  private Profile profile;

}
