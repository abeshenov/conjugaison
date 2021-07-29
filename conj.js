//------------------------------------------------------------
// Basic confuguration

const subj = [ "je/j'", "tu", "il", "nous", "vous", "ils" ];

// Tenses to be displayed:
const tenses = [
    [ "present", "présent" ],
    [ "imparfait", "imparfait" ],
    [ "passeCompose", "passé composé" ],
    [ "futurSimple", "futur simple" ],
    [ "subjonctifPresent", "subjonctif" ],
    [ "conditionnelPresent", "conditionnel" ],
    [ "imperatif", "impératif" ]
];

// Basic fields to be displayed:
const basicFields = [
    ["participePresent", "participe présent"],
    ["participePasse", "participe passé"],
    ["auxiliaire", "auxiliaire", 1],
    ["formePronominale", "forme pronominale", 1],
    ["formeNonPronominale", "forme non pronominale", 1]
];

//------------------------------------------------------------
// Make a verb list from verbs.json

function addVerbLink (parent, verb) {
    const a = document.createElement("a");
    a.textContent = verb;
    a.setAttribute("href", "#"+verb);
    a.setAttribute("onclick", "setVerb(\""+verb+"\")");
    parent.appendChild(a);
}

function makeVerbListFromJSON(data) {
    const div = document.getElementById("verb-list");

    for (let i=0; i < data.length; i++) {
	const ul = document.createElement("ul");

	for (let j=0; j < data[i].length; j++) {
	    const li = document.createElement("li");
	    addVerbLink (li, data[i][j]);
	    ul.appendChild(li);
	}
	div.appendChild(ul);
    }
}

function makeVerbList() {
    const requestURL = './verbs.json';
    const request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();

    request.onload = function() {
	makeVerbListFromJSON(request.response);
    }
}

//------------------------------------------------------------
// Update basic info about the verb

function updateBasicInfo() {
    const basicDiv = document.getElementById("verb-basics");
    basicDiv.textContent = '';

    const h2 = document.createElement("h2");
    h2.textContent = data['infinitif'];
    h2.setAttribute("id", data['infinitif']);

    basicDiv.appendChild(h2);

    const table = document.createElement("table");
    for (let i=0; i < basicFields.length; i++) {
	if (data.hasOwnProperty(basicFields[i][0])) {
	    const tr = document.createElement("tr");

	    const th = document.createElement("th");
	    th.textContent = basicFields[i][1] + ":";

	    
	    const td = document.createElement("td");

	    if (basicFields[i].length == 3 &&
		basicFields[i][2] == 1 &&
		data[basicFields[i][0]] !== "–") {
		    addVerbLink (td, data[basicFields[i][0]]);
	    }
	    else {
		td.textContent = data[basicFields[i][0]];
	    }

		tr.appendChild(th);
	    tr.appendChild(td);
	    table.appendChild(tr);
	}
    }

    basicDiv.appendChild(table);
}

//------------------------------------------------------------

function addTenseList () {
    const ul = document.getElementById("tense-list");
    for (let i=0; i < tenses.length; i++) {
	const li = document.createElement("li");
	li.setAttribute("id", tenses[i][0]);
	const a = document.createElement("a");
	a.textContent = tenses[i][1];
	a.setAttribute("href", "javascript:setTense('"+tenses[i][0]+"')");
	li.appendChild(a);
	ul.appendChild(li);
    }
}

function setActiveTense(tense) {
    const oldLi = document.getElementById(currentTense);
    oldLi.setAttribute("class", "");

    const newLi = document.getElementById(tense);
    newLi.setAttribute("class", "active");
}

function setTense(tense) {
    if (tense !== currentTense) {
	setActiveTense(tense);
	currentTense = tense;
	updateConjugation();
    }
}

//------------------------------------------------------------

function setVerb(verb) {
    currentVerb = verb;
    updateConjugation();
}

//------------------------------------------------------------

function updateConjugationFromJSON(data) {
    const conjDiv = document.getElementById("conj");
    conjDiv.textContent = '';

    const table = document.createElement("table");

    for (let i=0; i < subj.length; i++) {
	const tr = document.createElement("tr");
	const th = document.createElement("th");
	const td = document.createElement("td");

	th.textContent = subj[i];

	if (data.hasOwnProperty(currentTense)) {
	    td.textContent = data[currentTense][i];
	}
	else {
	    td.textContent = "–";
	}

	tr.appendChild(th);
	tr.appendChild(td);
	table.appendChild(tr);
    }
    conjDiv.appendChild(table);
}

//------------------------------------------------------------
// Global variables

var currentVerb = null;
var currentTense = null;
var data = null;

function updateConjugation() {
    if (data !== null && currentVerb !== null && data['infinitif'] === currentVerb) {
	updateConjugationFromJSON(data);
    }
    else {
	document.getElementById("main").setAttribute("class", "opaque");

	const requestURL = './json/' + currentVerb + '.json';
	const request = new XMLHttpRequest();
	request.open('GET', requestURL);
	request.responseType = 'json';
	request.send();

	request.onload = function() {
	    data = request.response;
	    updateConjugationFromJSON(data);
	    updateBasicInfo();
	    location.replace("#" + currentVerb);
	    document.getElementById("main").setAttribute("class", "");
	}
    }
}

//------------------------------------------------------------
// Initialize global variables:

function init() {
    if (window.location.hash) {
	currentVerb = decodeURI(window.location.hash.substring(1, window.location.hash.length));
    }
    else {
	currentVerb = "être";
    }

    if (currentTense === null) {
	currentTense = "present";
    }

    updateConjugation();
}

addTenseList();
makeVerbList();
init();
setActiveTense(currentTense);

window.onhashchange = function() {
    init();
}
