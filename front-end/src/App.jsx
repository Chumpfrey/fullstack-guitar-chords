import { useState, useEffect } from 'react'
import guitarLogo from './assets/guitar.png'
import './App.css'

function DisplayUniqueLayout({chord, index, displayedChords, setDisplayedChords}) {
  const NUM_POSITIONS = 6;
  const STRING_Y_POS_SCALE = 53;
  const [layoutIndex, changeLayoutIndex] = useState(0);
  const frets = [
    0,
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8,
    9,
    10,
    11,
    12,
    13,
    14,
    15,
    16,
    17
  ];

  function handleCloseClick(index) {
    if(index != -1) {
      var result = displayedChords.slice();
      result.splice(index, 1);
      setDisplayedChords(result);
    }
  }

  function smallestElement(list) {
    if(list.length < 0)
      return 0;

    var result = list[0];

    for(let step = 0; step < list.length; ++step) {
      if(list[step] < result && list[step] >= 0)
        result = list[step];
    }

    return result;
  }

  return (
    <div key={chord.id} className="tracked-chord">
      <div className="control-display">
        <button className="control-close-button" onClick={() => handleCloseClick(index)}>x</button>
      </div>
      <div className="chord-display-title">
        <button className="right-button" onClick={() => changeLayoutIndex((layoutIndex + 1) % chord.chordShapes.length)}>
          &gt;
        </button>
        <div className="display-title">
          {chord.name} {chord.type} {smallestElement(chord.chordShapes[layoutIndex].frets)} {chord.chordShapes[layoutIndex].frets}
        </div>
        <button className="left-button" onClick={() => changeLayoutIndex((layoutIndex - 1) < 0 ? (chord.chordShapes.length - 1) : layoutIndex - 1)}>
          &lt;
        </button>
      </div>
      <div className="chord-display">
        <div className="fretboard">
          <div className="string-first">
            {frets.map((pos, i) => (
              chord.chordShapes[layoutIndex].frets[0] != 0 && 
              chord.chordShapes[layoutIndex].frets[0] === pos && 
              <div className="dot" style={{top: `${smallestElement(chord.chordShapes[layoutIndex].frets) >= 3 ? (((pos - smallestElement(chord.chordShapes[layoutIndex].frets)) % NUM_POSITIONS) * STRING_Y_POS_SCALE) + ((3 * STRING_Y_POS_SCALE) / 2) : ((pos % NUM_POSITIONS) * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
            ))}
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret-last"/>
          </div>
          <div className="string">
            {frets.map((pos, i) => (
              chord.chordShapes[layoutIndex].frets[1] != 0 && 
              chord.chordShapes[layoutIndex].frets[1] === pos && 
              <div className="dot" style={{top: `${smallestElement(chord.chordShapes[layoutIndex].frets) >= 3 ? (((pos - smallestElement(chord.chordShapes[layoutIndex].frets)) % NUM_POSITIONS) * STRING_Y_POS_SCALE) + ((3 * STRING_Y_POS_SCALE) / 2) : ((pos % NUM_POSITIONS) * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
            ))}
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret-last"/>
          </div>
          <div className="string">
            {frets.map((pos, i) => (
              chord.chordShapes[layoutIndex].frets[2] != 0 && 
              chord.chordShapes[layoutIndex].frets[2] === pos && 
              <div className="dot" style={{top: `${smallestElement(chord.chordShapes[layoutIndex].frets) >= 3 ? (((pos - smallestElement(chord.chordShapes[layoutIndex].frets)) % NUM_POSITIONS) * STRING_Y_POS_SCALE) + ((3 * STRING_Y_POS_SCALE) / 2) : ((pos % NUM_POSITIONS) * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
            ))}
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret-last"/>
          </div>
          <div className="string">
            {frets.map((pos, i) => (
              chord.chordShapes[layoutIndex].frets[3] != 0 && 
              chord.chordShapes[layoutIndex].frets[3] === pos && 
              <div className="dot" style={{top: `${smallestElement(chord.chordShapes[layoutIndex].frets) >= 3 ? (((pos - smallestElement(chord.chordShapes[layoutIndex].frets)) % NUM_POSITIONS) * STRING_Y_POS_SCALE) + ((3 * STRING_Y_POS_SCALE) / 2) : ((pos % NUM_POSITIONS) * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
            ))}
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret-last"/>
          </div>
          <div className="string">
            {frets.map((pos, i) => (
              chord.chordShapes[layoutIndex].frets[4] != 0 && 
              chord.chordShapes[layoutIndex].frets[4] === pos && 
              <div className="dot" style={{top: `${smallestElement(chord.chordShapes[layoutIndex].frets) >= 3 ? (((pos - smallestElement(chord.chordShapes[layoutIndex].frets)) % NUM_POSITIONS) * STRING_Y_POS_SCALE) + ((3 * STRING_Y_POS_SCALE) / 2) : ((pos % NUM_POSITIONS) * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
            ))}
            {frets.map((pos, i) => (
              chord.chordShapes[layoutIndex].frets[5] != 0 && 
              chord.chordShapes[layoutIndex].frets[5] === pos && 
              <div className="dot-two" style={{top: `${smallestElement(chord.chordShapes[layoutIndex].frets) >= 3 ? (((pos - smallestElement(chord.chordShapes[layoutIndex].frets)) % NUM_POSITIONS) * STRING_Y_POS_SCALE) + ((3 * STRING_Y_POS_SCALE) / 2) : ((pos % NUM_POSITIONS) * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
            ))}
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret"/>
            <div className="fret-last"/>
          </div>
        </div>
      </div>
      <div className="chord-display-label">
        <div style={{marginLeft: "-20px"}}>{chord.chordShapes[layoutIndex].fingers[0] != 0 && 
          chord.chordShapes[layoutIndex].fingers[0]}</div>
        <div style={{marginLeft: "-12px"}}>{chord.chordShapes[layoutIndex].fingers[1] != 0 && 
          chord.chordShapes[layoutIndex].fingers[1]}</div>
        <div style={{marginLeft: "-2px"}}>{chord.chordShapes[layoutIndex].fingers[2] != 0 && 
          chord.chordShapes[layoutIndex].fingers[2]}</div>
        <div style={{marginRight: "-4px"}}>{chord.chordShapes[layoutIndex].fingers[3] != 0 && 
          chord.chordShapes[layoutIndex].fingers[3]}</div>
        <div style={{marginRight: "-15px"}}>{chord.chordShapes[layoutIndex].fingers[4] != 0 && 
          chord.chordShapes[layoutIndex].fingers[4]}</div>
        <div style={{marginRight: "-20px"}}>{chord.chordShapes[layoutIndex].fingers[5] != 0 && 
          chord.chordShapes[layoutIndex].fingers[5]}</div>
      </div>
    </div>
  );
}

function App() {
  const [chords, setChords] = useState([]);
  const [displayedChords, setDisplayedChords] = useState([]);
  const [selectedNote, setSelectedNote] = useState("");
  const [selectedType, setSelectedType] = useState("");
  
  const notes = [
    "A",
    "A#",
    "B",
    "C",
    "C#",
    "D",
    "D#",
    "E",
    "F",
    "F#",
    "G",
    "G#",
  ];
  const types = [
    "[Type]",
    "Major",
    "Minor"
  ];

  useEffect(() => {
    fetch("http://192.168.1.85:8080/chords")
      .then(response => response.json())
      .then(data => setChords(data));
  }, []);

  function locateChord(chord) {
    if(chord.name.toLowerCase() == selectedNote.toLowerCase() && chord.type.toLowerCase() == selectedType.toLowerCase())
      setDisplayedChords([...displayedChords, chord]);
  }

  function handleGenClick() {
    if(selectedNote == "" && selectedType == "")
      alert("Please select a note and type to generate.");
    else if(selectedNote == "")
      alert("Please select a note.");
    else if(selectedType == "[Type]")
      alert("Please select a type.");
    else {
      chords.forEach(locateChord);
    }
  }

  function handleNoteClick(item) {
    setSelectedNote(item);
  }

  function handleResetClick() {
    setDisplayedChords([]);
  }

  return (
    <div>
      <div id="header">
        <h1 id="title">
          Guitar Chord Dictionary
        </h1>

        <img src={guitarLogo} id="guitarLogo">
        </img>
      </div>

      <div id="containers">
        <div id="note-container">
          {notes.map((item, index) => 
            <div key={index}>
              <button className={selectedNote === item ? "note-button selected" : "note-button"} onClick={() => handleNoteClick(item)}>
                {item}
              </button>
            </div>
          )}
        </div>

        <select id="type-container"
          value={selectedType}
          onChange={(event) => setSelectedType(event.target.value)}
        >
          {types.map((item, index) => 
            <option key={index}>
              {item}
            </option>
          )}
        </select>

        <div id="track-op-container">
          <button className="track-op-buttons" onClick={handleGenClick}>
            Generate
          </button>
          <button className="track-op-buttons" onClick={handleResetClick}>
            Reset
          </button>
        </div>
      </div>
      <div id="chord-track">
        {displayedChords.map((chord, index) => (
          <DisplayUniqueLayout key={chord.id} chord={chord} index={index} displayedChords={displayedChords} setDisplayedChords={setDisplayedChords}></DisplayUniqueLayout>
        ))}
      </div>
    </div>
  );
}

export default App
