import{r as n,i as c,F as u}from"./S0V1hgl_.js";const i=JSON.parse(localStorage.getItem("cartItems"))||[],o=n(i),r=c(()=>o.value.reduce((e,t)=>e+t.quantity,0)),l=c(()=>o.value.reduce((e,t)=>e+t.precio*t.quantity,0)),d=e=>{if(e.stock>0){const t=o.value.find(a=>a.idProducto===e.idProducto);t?e.stock>=0&&(t.quantity+=1):o.value.push({...e,quantity:1})}},f=e=>{const t=o.value.find(a=>a.idProducto===e);console.log(t.stock),console.log(t.quantity),t&&t.stock>0&&(t.quantity+=1,t.stock-=1)},v=e=>{const t=o.value.find(a=>a.idProducto===e);t&&t.quantity>0?(t.quantity-=1,t.stock+=1):t&&s(e)},s=e=>{const t=o.value.findIndex(a=>a.idProducto===e);t!==-1&&(o.value[t].stock+=o.value[t].quantity,o.value.splice(t,1))},y=()=>{o.value=[]};u(o,e=>{localStorage.setItem("cartItems",JSON.stringify(e))},{deep:!0});const q=()=>({cartItems:o,cartCount:r,cartTotal:l,addToCart:d,increaseQuantity:f,decreaseQuantity:v,removeItem:s,clearCart:y});export{q as u};
