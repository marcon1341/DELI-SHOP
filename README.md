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


## 📸 Sample Screens

![Screenshot 2025-05-28 142309](https://github.com/user-attachments/assets/e32705d0-9b33-46f2-a114-6fe3e95f0546)
![Screenshot 2025-05-28 142732](https://github.com/user-attachments/assets/15c6fbec-da89-411e-8fa2-7d7b2a99ebc6)
![Screenshot 2025-05-28 142818](https://github.com/user-attachments/assets/ae57155f-f99f-44fb-8970-500b96fc3cd4)
![Screenshot 2025-05-28 142920](https://github.com/user-attachments/assets/a08ba82b-935b-4299-a0a3-69d2afced24a)


**Special Sandwich Screen**

![Screenshot 2025-05-28 143648](https://github.com/user-attachments/assets/e0d4ab3a-313f-4c58-aad0-b7fdff65cac0)




**Drink and chips screen**


![Screenshot 2025-05-28 143444](https://github.com/user-attachments/assets/20761ba1-5c9c-45a1-a3b9-966f5c661030)
![Screenshot 2025-05-28 143419](https://github.com/user-attachments/assets/3aa54795-14d2-4515-ac47-f2a28da77353)




