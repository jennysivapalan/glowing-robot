function CutsCheck() {
    var dataUri = '/check';
    var mapitUri = 'http://mapit.mysociety.org/postcode/';

    function init() {
        document.querySelector('form').addEventListener('submit', function (e) { e.preventDefault(); query(); });
    }

    function query() {
        document.querySelector('form button').disabled = true;
	var postcode = document.querySelector('[name=postcode]').value;	
	var postcodeCallback = function (postcode) {
	    getData(postcode, dataCallback);
	}
	var dataCallback = function (result) {
	    showResult(result);
	    document.querySelector('form button').disabled = false;
	}
	resolvePostcode(postcode, postcodeCallback);
    }

    function resolvePostcode(postcode, callback) {
	var runMapitUri = mapitUri + postcode;
	var mapitReq = new XMLHttpRequest();
	mapitReq.open('GET', runMapitUri);
	mapitReq.onload = function (e) {
	    if (this.status == 200) {
		var result = JSON.parse(this.response);
		var wmc = result.shortcuts.WMC;
		var gss = result.areas[wmc].codes.gss;
		callback(gss);
	    }
	}
	mapitReq.send();
    }

    function getData(ons, callback) {
        var runQueryUri = dataUri + '?ons=' + ons;
        var queryReq = new XMLHttpRequest();
        queryReq.open('GET', runQueryUri);
        queryReq.onload = function (e) {
            if (this.status == 200) {
                var result = JSON.parse(this.response);
		callback(result);
            }
        }
        queryReq.send();
    }

    function showResult(result) {
        var resultDisplay = document.createElement('p');
        var html = 'The cuts will affect you by...' + result;
        resultDisplay.innerHTML = html;
        document.querySelector('.result').appendChild(resultDisplay);
    }

    init();
}

document.addEventListener('DOMContentLoaded', new CutsCheck());