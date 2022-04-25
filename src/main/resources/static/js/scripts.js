function onEnter(element) {
if ((element.value !==undefined && element.value.length >=0) ||$(this).attr('placeholder') !==null) {
element.parentNode.querySelector("input").classList.add("active");
}
}

function onExit(element) {
if ((element.value !==undefined && element.value.length ==0) ||$(this).attr('placeholder') ===null) {
element.parentNode.querySelector("input").classList.remove("active");
}
}
