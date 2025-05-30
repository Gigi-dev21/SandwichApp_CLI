<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
</head>
<body>
  <h1>🥪 DELI-cious Application</h1>
<p>
  This project is a digital Point of Sale (POS) system for <strong>DELI-cious</strong>, a custom sandwich shop. 
  The goal is to replace the existing paper-based order system with a faster and more efficient computer-based solution.
</p>
 <p>I have developed two versions of the Point of Sale (POS) system:</p>

  <ul>
    <li>
      <strong>Command-Line Interface (CLI):</strong>
      <a href="https://github.com/Gigi-dev21/SandwichApp_CLI">Current GitHub</a>
    </li>
    <li>
      <strong>Graphical User Interface (GUI) using JavaFX:</strong>
      <a href="https://github.com/Gigi-dev21/SandwichApp_GUI">GUI Version GitHub Link</a>
    </li>
  </ul>

<br/>
<h2>✅ Data Handling</h2>
<ul>
  <li>
    The inventory data is stored separately in its own JSON file.
  </li>
    <br/>
  <li>
    The application uses the Jackson JSON library to read both the transactions and inventory files, mapping the data into Java objects.
  </li>
  <br/>
  <li>
    When a customer places and confirms an order, the order details are saved as a transaction in a dedicated JSON file. Additionally, a separate text file is generated as 
    a receipt.
  </li>
    <br/>
  <li>
    This enables the system to dynamically load and display the current inventory as well as all recorded transactions.
  </li>
</ul>
<br/>

  <h2>✅ User Flow Diagram</h2>
  <p>This diagram illustrates the user interface flow of the application:</p>
  <img src="src/Images/UI.png" alt="UI Diagram" />
  <br/>
  
  <h2>✅ Class Diagram</h2>
  <p>This diagram represents the class structure of the system:</p>
  <img src="src/Images/Classesdiagram.png" alt="Class Diagram" />



 <br/>
  <h2>✅ Admin Home Page</h2>
  <p>
    Access to this page requires a password.  
    After logging in, the admin can view all transactions in a table format, including total sales.
  </p>
  <img src="src/Images/AdminPage.png" alt="Admin Page UI" />

 <br/>

  <h2>✅ User Home Page</h2>
  <p>
     Users must enter their name before continuing with the order process.
  </p>
  <img src="src/Images/UserPage.png" alt="User Page UI" />

<br/>

  <h2>✅ Checkout Page</h2>
  <p>
     Once items are selected, the checkout page displays a summary of the order, the total cost and payment sytstem.
  </p>
  <img src="src/Images/Checkout.png" alt="Checkout Page UI" />
  
  <br/>
<p>📷 More screenshots of the application’s screens can be found in the <code>images</code> folder.</p>

  


<br/>

  <h2>✅ Interesting piece of my code</h2>
  <p>
   This class uses Jackson’s ObjectMapper to automatically read JSON keys and assign values to matching Java object properties. I find this interesting because it simplifies data binding, making it easy to load complex inventory data dynamically with minimal code.
  </p>
    <br/>
  <img src="src/Images/InterstesingCode.png" alt="Checkout Page UI" />

  <br/>
  <h1>✅(GUI) using JavaFX</h1>
  <br>
     <a href="https://github.com/Gigi-dev21/SandwichApp_GUI">GUI Version GitHub Link</a>
       <br>
       <br>
      <img src="src/Images/screen4.png" alt="Checkout Page UI" />
      <img src="src/Images/screen2.png" alt="Checkout Page UI" />
      <img src="src/Images/screen3.png" alt="Checkout Page UI" />
      <img src="src/Images/screen1.png" alt="Checkout Page UI" />
</body>
</html>
