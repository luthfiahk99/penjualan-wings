var moneyConversions = document.querySelectorAll(".moneyConversion");
for(let element of moneyConversions){
	let convertedValue = Number(element.value).toFixed(2);
	element.value = convertedValue;
}
var numberInputs = document.querySelectorAll("[type=number]");
for(let element of numberInputs){
	if(element.value == ""){
		element.value = 0;
	}
}
var dateInputs = document.querySelectorAll("[type=date]");
for(let element of dateInputs){
	if(element.getAttribute("value") != ""){
		let dateValue = new Date(element.getAttribute("value"));
		let formatted = dateValue.toISOString().split('T')[0];
		element.value = formatted;
	}
}
let alternateAction = document.querySelector(".alternate-action table");
if(alternateAction != null){
	let actionType = alternateAction.getAttribute("data-action");
	document.querySelector(".alternate-action").setAttribute("action", actionType);
	if(actionType === "update"){
		document.querySelector(".readonly-id").setAttribute("readonly", "readonly");
	}
}
let role = document.querySelector(".role");
if(role != null){
	let roleName = role.textContent;
	let formatted = roleName.replace('[', '').replace(']', '').replace('ROLE_', '');
	role.textContent = formatted;
}
let mainBody = document.querySelector(".main-body");
let roleName = mainBody.getAttribute("data-role");
let formatted = roleName.replace('[', '').replace(']', '').replace('ROLE_', '');
mainBody.setAttribute("data-role", formatted);



let qty = 0;
let quantity =0;

addQuantityEventListener;

function addQuantityEventListener(){
    let quantityTextBox = document.querySelector("#quantity");
        quantityTextBox.addEventListener("blur", function(event){
        quantity = quantityTextBox.value;
        console.log(qty)
//        changeSubTotal();
        countSubTotal();
        });
}

function changeSubTotal(){
    if(qty == 0){
        document.getElementById("subTotal").value = 0;
    } else{
        let priceQty = document.getElementById("price").value;
        let totalPrice = qty * priceDays;
        document.getElementById("subTotal").value = totalPrice;
        document.getElementById("showSubTotal").innerHtml = Rupiah.format(totalPrice);
    }
}

var Rupiah=Intl.NumberFormat("id-ID", {
      style: "currency",
      currency: "IDR"
    })

function countSubTotal(){
    let quantity =document.getElementById("quantity").value;
    let payPerQty=document.getElementById("price").value;
    let total=0;
    if(quantity != 0){
         total = payPerQty*quantity;
    }
    document.getElementById("subTotal").value=isNaN(total)?0:Math.max(total,0);
    console.log(isNaN(total)?0:Math.max(total,0))
    document.getElementById("showSubTotal").innerHTML=Rupiah.format(isNaN(total)?0:Math.max(total,0));
    console.log(Rupiah.format(isNaN(total)?0:Math.max(total,0)))
}