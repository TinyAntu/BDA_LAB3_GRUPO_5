import{u as f}from"./B1HfQ2J4.js";import{a as _}from"./CCb-kr4I.js";import{r as n,F as b,o as h,g as x,v as i,x as o,K as v,L as y,z as k,t as c,y as r,M as C,J as w}from"./S0V1hgl_.js";const P={class:"container mx-auto px-4"},V={class:"grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6"},A={class:"text-xl font-semibold mb-2"},B={class:"text-gray-600 mb-2"},L={class:"flex justify-between items-center mb-4"},M={class:"text-xl font-bold"},S=["onClick","disabled"],z="http://localhost:8080/api",U={__name:"productos",setup(E){const d=n([]),l=n(1),m=n(1),{addToCart:g}=f(),u=async()=>{const e=(l.value-1)*8;try{const s=await _.get(`${z}/producto`,{params:{limit:8,offset:e}});d.value=s.data.products,m.value=Math.ceil(s.data.totalCount/8)}catch(s){console.error("Error fetching products:",s)}};b(l,u),h(u);const p=a=>a===0?"text-red-500":a<=5?"text-orange-500":"text-green-500";return(a,e)=>{const s=x("v-pagination");return c(),i("div",P,[e[1]||(e[1]=o("h1",{class:"text-3xl font-bold mb-8"},"Productos",-1)),o("div",V,[(c(!0),i(v,null,y(d.value,t=>(c(),i("div",{key:t.id,class:"border border-gray-300 rounded-lg shadow-md p-4"},[o("h2",A,r(t.nombre),1),o("p",B,r(t.descripcion),1),o("div",L,[o("span",M,"$"+r(t.precio),1),o("span",{class:C(["font-medium",p(t.stock)])}," Stock: "+r(t.stock),3)]),o("button",{onClick:()=>{w(g)(t),console.log(a.cartItems.value)},class:"w-full bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 disabled:bg-gray-400",disabled:t.stock===0},r(t.stock===0?"Agotado":"Agregar a carrito"),9,S)]))),128))]),k(s,{modelValue:l.value,"onUpdate:modelValue":e[0]||(e[0]=t=>l.value=t),length:m.value,color:"primary",class:"mt-6"},null,8,["modelValue","length"])])}}};export{U as default};
