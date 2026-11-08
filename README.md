# UniAccounts - Travail 1

Systeme de gestion des comptes etudiants de l'universite.
Ce premier travail utilise trois patterns creationnels.

## Patterns utilises

| Pattern   | Classe                              | Role |
|-----------|-------------------------------------|------|
| Builder   | StudentAccount.Builder              | Construire un compte etape par etape avec validation |
| Singleton | AccountRepository, AuditLogger      | Un seul depot et un seul journal d'audit |
| Prototype | StudentAccount.cloneFor()           | Creer les comptes d'une promotion a partir d'un modele |

## Structure

- StudentAccount.java : le compte etudiant (+ Builder + Prototype)
- AccountRepository.java : depot unique des comptes (Singleton)
- AuditLogger.java : journal d'audit unique (Singleton)
- Main.java : programme de demonstration
- docs/ : captures d'ecran

## Execution

Prerequis : JDK 17 ou plus.

    javac *.java
    java Main

## Resultat attendu

Voir docs/capture1.png