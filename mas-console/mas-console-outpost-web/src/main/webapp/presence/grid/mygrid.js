require([ "dojo/data/ItemFileWriteStore", "dojox/grid/EnhancedGrid",
		"dojox/grid/enhanced/plugins/Filter",
		"dojox/grid/enhanced/plugins/exporter/CSVWriter",
		"dojox/grid/enhanced/plugins/Printer",
		"dojox/grid/enhanced/plugins/Cookie",
		"dojox/grid/enhanced/plugins/IndirectSelection",
		"dojox/grid/enhanced/plugins/NestedSorting",
		"dojox/grid/enhanced/plugins/Selector",
		"dojox/grid/enhanced/plugins/Menu", "dojox/grid/enhanced/plugins/DnD",
		"dojox/grid/enhanced/plugins/Search",
		"dojox/grid/enhanced/plugins/CellMerge",
		"dojox/grid/enhanced/plugins/Pagination",
		"dojo/domReady!"], function() {
	var data = {
		identifier : 'id',
		label : 'id',
		items : []
	};
	var data_list = [ {
		"Heard" : true,
		"Checked" : "True",
		"Genre" : "Easy Listening",
		"Artist" : "Bette Midler",
		"Year" : 2003,
		"Album" : "Bette Midler Sings the Rosemary Clooney Songbook",
		"Name" : "Hey There",
		"Length" : "03:31",
		"Track" : 4,
		"Composer" : "Ross, Jerry 1926-1956 -w Adler, Richard 1921-",
		"Download Date" : "1923/4/9",
		"Last Played" : "04:32:49"
	}, {
		"Heard" : true,
		"Checked" : "True",
		"Genre" : "Classic Rock",
		"Artist" : "Jimi Hendrix",
		"Year" : 1993,
		"Album" : "Are You Experienced",
		"Name" : "Love Or Confusion",
		"Length" : "03:15",
		"Track" : 4,
		"Composer" : "Jimi Hendrix",
		"Download Date" : "1947/12/6",
		"Last Played" : "03:47:49"
	}, {
		"Heard" : true,
		"Checked" : "True",
		"Genre" : "Jazz",
		"Artist" : "Andy Narell",
		"Year" : 1992,
		"Album" : "Down the Road",
		"Name" : "Sugar Street",
		"Length" : "07:00",
		"Track" : 8,
		"Composer" : "Andy Narell",
		"Download Date" : "1906/3/22",
		"Last Played" : "21:56:15"
	}, {
		"Heard" : true,
		"Checked" : "True",
		"Genre" : "Progressive Rock",
		"Artist" : "Emerson, Lake & Palmer",
		"Year" : 1992,
		"Album" : "The Atlantic Years",
		"Name" : "Tarkus",
		"Length" : "20:40",
		"Track" : 5,
		"Composer" : "Greg Lake/Keith Emerson",
		"Download Date" : "1994/11/29",
		"Last Played" : "03:25:19"
	}, {
		"Heard" : true,
		"Checked" : "True",
		"Genre" : "Rock",
		"Artist" : "Blood, Sweat & Tears",
		"Year" : 1968,
		"Album" : "Child Is Father To The Man",
		"Name" : "Somethin' Goin' On",
		"Length" : "08:00",
		"Track" : 9,
		"Composer" : "",
		"Download Date" : "1973/9/11",
		"Last Played" : "19:49:41"
	}, {
		"Heard" : true,
		"Checked" : "True",
		"Genre" : "Jazz",
		"Artist" : "Andy Narell",
		"Year" : 1989,
		"Album" : "Little Secrets",
		"Name" : "Armchair Psychology",
		"Length" : "08:20",
		"Track" : 5,
		"Composer" : "Andy Narell",
		"Download Date" : "2010/4/15",
		"Last Played" : "01:13:08"
	}, {
		"Heard" : true,
		"Checked" : "True",
		"Genre" : "Easy Listening",
		"Artist" : "Frank Sinatra",
		"Year" : 1991,
		"Album" : "Sinatra Reprise: The Very Good Years",
		"Name" : "Luck Be A Lady",
		"Length" : "05:16",
		"Track" : 4,
		"Composer" : "F. Loesser",
		"Download Date" : "2035/4/12",
		"Last Played" : "06:16:53"
	}, {
		"Heard" : true,
		"Checked" : "True",
		"Genre" : "Progressive Rock",
		"Artist" : "Dixie dregs",
		"Year" : 1977,
		"Album" : "Free Fall",
		"Name" : "Sleep",
		"Length" : "01:58",
		"Track" : 6,
		"Composer" : "Steve Morse",
		"Download Date" : "2032/11/21",
		"Last Played" : "08:23:26"
	} ];
	var len = data_list.length;
	var rounds = 25;
	for (var i = 0; i < rounds * len; ++i) {
		data.items.push(dojo.mixin({
			'id' : i + 1
		}, data_list[i % len]));
	}
	var store = new dojo.data.ItemFileWriteStore({
		data : data
	});
	
	var layout = {//--------------------------------------------------------------------------0
			defaultCell: {editable: true, autoComplete:true, type: dojox.grid.cells._Widget},
			cells:
			[
				{ field: "id", name:"Index", datatype:"number", width: 4},
				{ field: "Genre", name:"Genre", datatype:"string", width: 10},
				{ field: "Artist", name:"Artist", datatype:"string", width: 10},
				{ field: "Year", name:"Year", datatype:"string", width: 6},
				{ field: "Album", name:"Album", datatype:"string", width: 10},
				{ field: "Name", name:"Name", datatype:"string", width: 8, disabledConditions: [
					"contains", "notcontains"
				]},
				{ field: "Length", name:"Length", datatype:"string", width: 6},
				{ field: "Track", name:"Track", datatype:"number", width: 5},
				{ field: "Composer", name:"Composer", datatype:"string", width: 12},
				{ field: "Download Date", name:"Download Date", datatype:"date", width: 12,
					navigatable: true, editable: false,
					dataTypeArgs: {
						datePattern: "yyyy/M/d"
					}
				},
				{ field: "Last Played", name:"Last Played", datatype:"time", width: 6,
					dataTypeArgs: {
						timePattern: "HH:mm:ss"
					}
				},
				{ field: "Heard", name: "Checked", datatype:"boolean", width: 6/*, type: dojox.grid.cells.CheckBox*/},
				{ field: "Checked", name:"Checked (Customized Label)", editable: false, datatype:"boolean", width: 15, dataTypeArgs: {
					trueLabel: "This sounds like a very old song.",
					falseLabel: "Never heard of this song."
				}}
			]
		};
	console.log("test");
	var mygrid = new dojox.grid.EnhancedGrid({
		"id": "grid",
		"store": store,
		"structure": layout});
	mygrid.placeAt(dojo.byId("gridContainer"));
	mygrid.startup();
});