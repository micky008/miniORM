package com.epsi.orm.epsiorm.orm.models;

/**
 *
 * @author Michael
 */
public class Client {

				private Integer id;
				private String tva;
				private Boolean isEntreprise;
				private String formeJuridique;
				private String siren;
				private String nom;
				private String prenom;
				private String raisonSociale;
				private String adresse;
				private String codePostal;
				private String ville;
				private String email;
				private String telephone;

				/**
				 * @return the id
				 */
				public Integer getId() {
								return id;
				}

				/**
				 * @param id the id to set
				 */
				public void setId(Integer id) {
								this.id = id;
				}

				/**
				 * @return the tva
				 */
				public String getTva() {
								return tva;
				}

				/**
				 * @param tva the tva to set
				 */
				public void setTva(String tva) {
								this.tva = tva;
				}

				/**
				 * @return the isEntreprise
				 */
				public Boolean getIsEntreprise() {
								return isEntreprise;
				}

				/**
				 * @param isEntreprise the isEntreprise to set
				 */
				public void setIsEntreprise(Boolean isEntreprise) {
								this.isEntreprise = isEntreprise;
				}

				/**
				 * @return the formeJuridique
				 */
				public String getFormeJuridique() {
								return formeJuridique;
				}

				/**
				 * @param formeJuridique the formeJuridique to set
				 */
				public void setFormeJuridique(String formeJuridique) {
								this.formeJuridique = formeJuridique;
				}

				/**
				 * @return the siren
				 */
				public String getSiren() {
								return siren;
				}

				/**
				 * @param siren the siren to set
				 */
				public void setSiren(String siren) {
								this.siren = siren;
				}

				/**
				 * @return the nom
				 */
				public String getNom() {
								return nom;
				}

				/**
				 * @param nom the nom to set
				 */
				public void setNom(String nom) {
								this.nom = nom;
				}

				/**
				 * @return the prenom
				 */
				public String getPrenom() {
								return prenom;
				}

				/**
				 * @param prenom the prenom to set
				 */
				public void setPrenom(String prenom) {
								this.prenom = prenom;
				}

				/**
				 * @return the raisonSociale
				 */
				public String getRaisonSociale() {
								return raisonSociale;
				}

				/**
				 * @param raisonSociale the raisonSociale to set
				 */
				public void setRaisonSociale(String raisonSociale) {
								this.raisonSociale = raisonSociale;
				}

				/**
				 * @return the adresse
				 */
				public String getAdresse() {
								return adresse;
				}

				/**
				 * @param adresse the adresse to set
				 */
				public void setAdresse(String adresse) {
								this.adresse = adresse;
				}

				/**
				 * @return the codePostal
				 */
				public String getCodePostal() {
								return codePostal;
				}

				/**
				 * @param codePostal the codePostal to set
				 */
				public void setCodePostal(String codePostal) {
								this.codePostal = codePostal;
				}

				/**
				 * @return the ville
				 */
				public String getVille() {
								return ville;
				}

				/**
				 * @param ville the ville to set
				 */
				public void setVille(String ville) {
								this.ville = ville;
				}

				/**
				 * @return the email
				 */
				public String getEmail() {
								return email;
				}

				/**
				 * @param email the email to set
				 */
				public void setEmail(String email) {
								this.email = email;
				}

				/**
				 * @return the telephone
				 */
				public String getTelephone() {
								return telephone;
				}

				/**
				 * @param telephone the telephone to set
				 */
				public void setTelephone(String telephone) {
								this.telephone = telephone;
				}

				public String toString() {
								return id + " " + nom + " " + prenom + " " + raisonSociale;
				}

}
