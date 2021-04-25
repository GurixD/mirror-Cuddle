"use strict";

let inputFile = document.getElementById('inputFile');
let img = document.getElementById('animalImg');

inputFile.onchange = () => readURL(inputFile);

function imgClick() {
    inputFile.click();
}

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            img.setAttribute('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]); // convert to base64 string
    }
}