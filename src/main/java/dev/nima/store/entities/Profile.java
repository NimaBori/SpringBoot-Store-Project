package dev.nima.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents extended user profile information.
 * Has a One-to-One relationship with the User entity, sharing the same ID.
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "profiles")
public class Profile {
  /**
   * Primary key, which is also the foreign key to the users table.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  /**
   * A short biography of the user.
   */
  @Column(name = "bio")
  private String bio;

  /**
   * User's primary phone number.
   */
  @Column(name = "phone_number")
  private String phoneNumber;

  /**
   * User's date of birth.
   */
  @Column(name = "date_of_birth")
  private String dateOfBirth;

  /**
   * Accumulated loyalty points for the user.
   * Defined as UNSIGNED in DB.
   */
  @Column(name = "loyalty_points")
  private Integer loyaltyPoints;

  /**
   * The user this profile belongs to.
   * MapsId ensures it uses the same ID as the User.
   */
  @OneToOne
  @JoinColumn(name = "id")
  @MapsId
  @ToString.Exclude
  private User user;

}
