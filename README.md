<h1><strong>DELI-Shop Point-of-Sale CLI🛒</strong></h1>
<h3><strong>Between the Bread🥪™</strong></h3>





![Screenshot 2025-05-27 210112](https://github.com/user-attachments/assets/791dcfdf-377a-4cd0-a0da-f7e19d1ca13a)






<p><strong>Project Overview</strong></p>
<p>DELI-Shop is a command-line Point-of-Sale (POS) application designed for a growing sandwich business. Built with Java using OOP principles, the system allows customers to create their own custom sandwiches, order special items, and complete checkout with tax, tip, and receipt generation.</p>
<p>Customers can:</p>
<p>🥪 Build custom sandwiches (3 sizes + choice of bread, toppings, extras, toast).</p>
<p>🥪 Order signature <strong>Special Sandwiches</strong> (BLT, Philly Cheese Steak, French Dip), with remove/extra-topping support.</p>
<p>🥤 Add drinks, chips, optional bag (🛍 $0.08), and tip (5–25%).</p>
<p>📜 See real-time subtotal, tip, tax (10.25%), and total.</p>
<p>📋 Save a timestamped receipt in <code>receipts/yyyyMMdd-hhmmss.txt</code>.</p>

<p><strong>What the App Does</strong></p>
<p>🏠 <strong>Home Screen</strong></p>
<p>- Welcome banner using ASCII</p>
<p>- Menu Options: Start New Order and Exit</p>

<p>📋 <strong>Order Menu</strong></p>
<p>- Add Sandwich: Build your own with full flexibility.</p>
<p>- Add Drink: Choose size and flavor.</p>
<p>- Add Chips: Choose from flavor.</p>
<p>- Add Special Sandwich: Pick a prebuilt recipe, then modify it.</p>
<p>- Checkout (subtotal, tip, tax, total, bag fee).</p>
<p>- Cancel Order: Discard and return to home.</p>

<p>🥪 <strong>Sandwich Builder</strong></p>
<p>- Choose size (4″, 8″, 12″).</p>
<p>- Choose bread (white, wheat, rye, wrap, ciabatta).</p>
<p>- Select categories of toppings (meat, cheese, extras, sauces and sides).</p>
<p>- Toast option.</p>
<p>- Unlimited extras (premium toppings cost extra).</p>

<p>🥙 <strong>Special Sandwiches</strong></p>
<p>- Offers fixed recipes like BLT, Philly Cheese Steak, and French Dip.</p>
<p>- Allows removal of preset toppings.</p>
<p>- Add extra toppings (charged per item).</p>
<p>- Toast option.</p>

<p>🍟 <strong>Add-Ons</strong></p>
<p>- Drinks: Choose size (S/M/L) and flavor; price based on size.</p>
<p>- Chips: Select from predefined flavors; flat price.</p>
<p>- Bag: Optional adds a $0.08 fee.</p>

<p>💳 <strong>Checkout & Receipt</strong></p>
<p>- Shows itemized list, bag fee, tip, tax (10.25%), and grand total.</p>
<p>- Saves timestamped receipt under <code>receipts/yyyyMMdd-hhmmss.txt</code>.</p>

<p><strong>Class Responsibilities</strong></p>

<p><strong>Main</strong></p>
<p>- Entry point of the application.</p>
<p>- Calls homeScreen() in the Menu class to initiate the app.</p>

<p><strong>Menu</strong></p>
<p>- Stores all items added (List&lt;PricedItem&gt;).</p>
<p>- Calculates subtotal, tax (fixed rate), bag fee, and tip.</p>
<p>- Uses LocalDateTime and DateTimeFormatter to timestamp receipts.</p>
<p>- Writes receipts using Files.writeString with StandardOpenOption.</p>
<p>- Method examples: addItem(), getTotal(), checkout(), addTip().</p>

<p><strong>PricedItem</strong></p>
<p>- Interface.</p>
<p>- Declares getPrice() to enforce price calculation behavior.</p>

<p><strong>Sandwich</strong></p>
<p>- Preloaded with default toppings.</p>
<p>- Supports removing/modifying toppings dynamically.</p>
<p>- Keeps track of extra toppings for separate pricing and receipt formatting.</p>

<p><strong>Topping</strong></p>
<p>- stores category (e.g., MEAT, CHEESE), name, and cost logic.</p>
<p>- Method: getPrice(int size) returns price based on sandwich size.</p>
<p>- Implements logic for free vs. premium topping behavior.</p>

<p><strong>Drink</strong></p>
<p>- Implements PricedItem.</p>
<p>- Fields: size and flavor.</p>
<p>- Static map stores size-based prices.</p>
<p>- Method: getPrice() returns cost based on selected size.</p>

<p><strong>Chips</strong></p>
<p>- Implements PricedItem.</p>
<p>- Stores selected flavor.</p>
<p>- Flat rate pricing regardless of flavor.</p>
<p>- Method: getPrice() returns fixed price.</p>

<p><strong>Symbol</strong></p>
<p>- Static utility class.</p>
<p>- Handles all ASCII banners.</p>
<p>- Keeps UI friendly and visually structured.</p>

**UML**

![Screenshot 2025-05-29 220155](https://github.com/user-attachments/assets/f2e9e333-3af3-4d7d-b031-b6c4a7b3589a)

## 📸 Sample Screens

![Screenshot 2025-05-29 204602](https://github.com/user-attachments/assets/70a20ba8-63c6-4d8f-9cbc-43428e7a3da7)

![Screenshot 2025-05-29 203331](https://github.com/user-attachments/assets/11412b8e-da23-4147-95ec-5e4f642ee905)

![Screenshot 2025-05-29 203306](https://github.com/user-attachments/assets/0ef4b031-601c-4e63-9735-9ce820414cc2)

![Screenshot 2025-05-29 203210](https://github.com/user-attachments/assets/f2a0d064-ef58-42d1-9b15-b3d4c99afcbc)

![Screenshot 2025-05-29 203109](https://github.com/user-attachments/assets/0807b1f1-d70c-4d93-9397-c8246c370a13)

![Screenshot 2025-05-29 203011](https://github.com/user-attachments/assets/eb0a2591-9716-4026-ab06-d0a22809cf88)

![Screenshot 2025-05-29 202949](https://github.com/user-attachments/assets/ce1dd1ef-017b-499f-9622-a3f4ca2d1c1c)

![Screenshot 2025-05-29 202811](https://github.com/user-attachments/assets/9bddead8-2d41-445d-bba6-c7f465c7d209)

![Screenshot 2025-05-29 202746](https://github.com/user-attachments/assets/5f81de7f-d26d-4eba-99ff-15ba8e5952d7)

![Screenshot 2025-05-29 202610](https://github.com/user-attachments/assets/bb6eae64-6376-4db0-ba17-616ef465a54f)

![Screenshot 2025-05-29 202534](https://github.com/user-attachments/assets/d24607b7-afb7-426a-b018-3464ff7677a6)

![Screenshot 2025-05-29 202514](https://github.com/user-attachments/assets/253de230-5790-4880-bf92-f3d319541aab)

![Screenshot 2025-05-29 202451](https://github.com/user-attachments/assets/6dee9493-7bac-4127-bb9e-03b16ba5efc0)

![Screenshot 2025-05-29 202238](https://github.com/user-attachments/assets/876c38f6-7b93-4243-8bf9-edabd199f502)

![Screenshot 2025-05-29 202022](https://github.com/user-attachments/assets/04517e4e-c4fc-4e49-be7d-b2cfe6de450a)










