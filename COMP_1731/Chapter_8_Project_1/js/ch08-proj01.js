
const tax_rate = prompt('Enter tax rate (0.10)');
const shipping_threshold = prompt('Enter shipping threshold (1000)');

/* add loop and other code here ... in this simple exercise we are not
   going to concern ourselves with minimizing globals, etc */

/* calcaulating subtotal */
let subtotal = 0;
for (let i = 0; i < cart.length; i++) {
   let item = cart[i];
   let total = calculateTotal(item.quantity, item.product.price);
   subtotal += total;
   outputCartRow(item, total);
}

/*calculating tax, shipping and grand total */
let tax = subtotal * tax_rate;
let shipping = (subtotal >= shipping_threshold) ? 0 : 40;
let grand_total = subtotal + tax + shipping;


/* restrucuring html code into JS */
document.write('<tr class="totals">');
document.write('<td colspan="4">Subtotal</td>');
document.write('<td>$' + subtotal.toFixed(2) + '</td>');
document.write('</tr>');

document.write('<tr class="totals">');
document.write('<td colspan="4">Tax</td>');
document.write('<td>$' + tax.toFixed(2) + '</td>');
document.write('</tr>');

document.write('<tr class="totals">');
document.write('<td colspan="4">Shipping</td>');
document.write('<td>$' + shipping.toFixed(2) + '</td>');
document.write('</tr>');

document.write('<tr class="totals">');
document.write('<td colspan="4" class="focus">Grand Total</td>');
document.write('<td class="focus">$' + grand_total.toFixed(2) + '</td>');
document.write('</tr>');