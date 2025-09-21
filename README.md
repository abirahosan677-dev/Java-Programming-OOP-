Overview

The PPE (Personal Protective Equipment) Inventory System is a Java-based application designed to manage the inventory of PPE items for healthcare organizations. It allows users to track PPE items, manage suppliers, and monitor transactions. This system helps streamline the supply chain and ensures hospitals have the necessary PPE during critical times.


Features

Inventory Management: Track PPE items available in the system.

Supplier Management: Manage supplier information for PPE items.

Transactions: Record transactions involving PPE items, including usage and replenishment.

User Management: Manage users within the system.

Reports: Generate reports on PPE inventory and transactions.


Directory Structure

src/: Contains the source code for the system.

build.xml: Ant build file for compiling the project.

manifest.mf: Project manifest metadata file.

ppe.txt: Contains information about PPE items in the inventory.

supplier_hospital.txt: Contains data on suppliers and hospitals.

transactions.txt: Log of transactions involving PPE items.

users.txt: List of users within the system.

test/: Contains unit tests for validating the functionality of the system.

build/: Output directory for the compiled code.

nbproject/: Configuration files for NetBeans.

Installation

Clone the repository:

git clone <repository_url>


Navigate to the project folder:

cd PPE_Inventory_System


Set up your Java environment (ensure JDK is installed).

Compile the project using Ant:

ant


The application will be compiled and available in the build/ directory.

Usage

Open the system using your preferred IDE (e.g., NetBeans).

Run the application and start managing PPE inventory and transactions.

Use the text files for data input (ppe.txt, supplier_hospital.txt, etc.).

Acknowledgments

This project was created as part of an educational assignment.
