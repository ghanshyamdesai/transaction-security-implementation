console.log("test");
function allowDrop(ev) {
  ev.preventDefault();
}

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.innerText);
}

function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("text");
  ev.target.appendChild(document.getElementById(data));
}
$(function() {
$('#confirmbutton').click(function() {
   
  /*  var result = $('#expensetable tbody').children().map(function () {
        var children = $(this).children();

        return "[" + children.eq(0).text() + "," + children.eq(1).text() + "]"
    }).get().join(",");*/
    /*$('#b tr').each(function(index, item) {
        var $item = $(item);
        myObjects.push({
            name: $item.find("td input[name='name']").val(),
            series: $item.find("td input[name='series']").val(),
            value: $item.find("td input[name='value']").val(),
        });
    });*/
    $.ajax({
        url: '/confirm',
        method: 'GET',
        contentType : 'application/json; charset=utf-8',
        //data: JSON.stringify(result)
    })
    .done(function(myObjects) {
        // handle success
    })
    .fail(function() {
        // handle fail
    });
});
});

function doSubmit()
{
var result = $('#expensetable tbody').children().map(function () {
    var children = $(this).children();

    return "[" + children.eq(0).text() + "," + children.eq(1).text() + "]"
}).get().join(",");

alert(result);
}

var category = document.getElementById("addCategory");
var modal = document.getElementById("myModal");
var spanArray = document.getElementsByTagName("span");
var btn = document.getElementsByTagName("myBtn");

category.onclick = function() {
	modal.style.display = "block";
}

btn.onclick = function() {
	alert("ANjalee")
}

//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}

//When the user clicks on <span> (x), close the modal
spanArray.onclick = function() {
  modal.style.display = "none";
}


$("#addCategory").live('click', function () {
    alert('clicked!');
});