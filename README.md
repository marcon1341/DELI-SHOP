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
![Screenshot 2025-05-29 202022](https://github.com/user-attachments/assets/04517e4e-c4fc-4e49-be7d-b2cfe6de450a)

![Screenshot 2025-05-29 202238](https://github.com/user-attachments/assets/9a0b3619-d68d-45aa-9918-c34d1e7cbc8a)

![Screenshot 2025-05-29 202451](https://github.com/user-attachments/assets/b957628c-bb1a-4583-827f-1d58c01b27b6)

![Screenshot 2025-05-29 202514](https://github.com/user-attachments/assets/0fe4668d-33b8-448c-b1c9-9dc615fdef3a)

![Screenshot 2025-05-29 202534](https://github.com/user-attachments/assets/a581aedf-4891-4db7-9e99-b9e09657df49)

![Screenshot 2025-05-29 202610](https://github.com/user-attachments/assets/09ba991d-9d66-4e42-b463-bad977b6f129)

![Screenshot 2025-05-29 202746](https://github.com/user-attachments/assets/580abf28-f4de-4d17-8f62-dab6b8160715)

![Screenshot 2025-05-29 202811](https://github.com/user-attachments/assets/00550c5a-4b6b-49f8-a2b4-cd8da68f226a)

![Screenshot 2025-05-29 202949](https://github.com/user-attachments/assets/fdb8ea7e-b9ad-49e2-96ac-0589c4cfa317)

![Screenshot 2025-05-29 203011](https://github.com/user-attachments/assets/728aca52-4c6c-4dd6-a79f-ada7bee339bb)

![Screenshot 2025-05-29 203109](https://github.com/user-attachments/assets/8d7a369f-bc6b-488a-af57-542417b960ed)

![Screenshot 2025-05-29 203210](https://github.com/user-attachments/assets/54322ec7-891c-4dca-a9f3-855fc7e77273)

![Screenshot 2025-05-29 203306](https://github.com/user-attachments/assets/86c1a469-e88b-4987-a702-76c00883d6df)

![Screenshot 2025-05-29 203331](https://github.com/user-attachments/assets/2d911f11-8928-4abb-b88c-a33638b8b224)

![Screenshot 2025-05-29 204602](https://github.com/user-attachments/assets/9dfc104a-1192-49c6-8a3f-8b7077ed09fd)

















