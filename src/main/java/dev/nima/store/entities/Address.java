package dev.nima.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a physical address associated with a user.
 * Each user can have multiple addresses (One-to-Many relationship).
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "addresses")
public class Address {
  /**
   * Primary key for the address.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  /**
   * Street name and number.
   */
  @Column(name = "street")
  private String street;

  /**
   * City where the address is located.
   */
  @Column(name = "city")
  private String city;

  /**
   * Postal code for the address.
   */
  @Column(name = "zip")
  private String zipCode;

  /**
   * The user who owns this address.
   * Linked via user_id foreign key.
   */
  @ManyToOne
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  private User user;

}
