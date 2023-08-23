BFF custom functionality:

The items on sale are chosen randomly, the number of items in each are 5 by default, but it can be set manually as well.
Discount catalog generation with random discount rates (from 5% to 30%).
All users, logged and guests, will be able to view the catalog.

The following information for each item is stored:
- Title
- Description
- Actual price
- Discount percent
- On sale price

The catalog's status is checked on every View, AddItemToCart and Purchase methods.

The catalog is active 10 minutes and then becomes inactive (flag), but not deleted.
After the old catalog expires a new one automatically generates. (There will always be items on sale.)
It is possible to have 2 or more catalogs at the same time.
If an item from the catalog is sold out it is automatically removed from it.

