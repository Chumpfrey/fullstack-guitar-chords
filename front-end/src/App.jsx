import { useState, useEffect } from 'react'
import guitarLogo from './assets/guitar.png'
import './App.css'

function App() {
  const STRING_Y_POS_SCALE = 53;

  const [chords, setChords] = useState([]);
  const [displayedChords, setDisplayedChords] = useState([]);
  const [selectedNote, setSelectedNote] = useState("");
  const [selectedType, setSelectedType] = useState("");
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

        <button id="generateButton" onClick={handleGenClick}>
          Generate
        </button>
      </div>

      <div id="chord-track">
        {displayedChords.map(chord => (
          <div key={chord.id} className="tracked-chord">
            <div className="chord-display-title">
              {chord.name} {chord.type} 
            </div>
            <div className="chord-display">
              <div className="fretboard">
                <div className="string-first">
                  {frets.map((pos, i) => (
                    chord.chordShapes[0].frets[0] != 0 && 
                    chord.chordShapes[0].frets[0] === pos && 
                    <div className="dot" style={{top: `${(pos * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
                  ))}
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret-last">
                  </div>
                </div>
                <div className="string">
                  {frets.map((pos, i) => (
                    chord.chordShapes[0].frets[1] != 0 && 
                    chord.chordShapes[0].frets[1] === pos && 
                    <div className="dot" style={{top: `${(pos * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
                  ))}
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret-last">
                  </div>
                </div>
                <div className="string">
                  {frets.map((pos, i) => (
                    chord.chordShapes[0].frets[2] != 0 && 
                    chord.chordShapes[0].frets[2] === pos && 
                    <div className="dot" style={{top: `${(pos * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
                  ))}
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret-last">
                  </div>
                </div>
                <div className="string">
                  {frets.map((pos, i) => (
                    chord.chordShapes[0].frets[3] != 0 && 
                    chord.chordShapes[0].frets[3] === pos && 
                    <div className="dot" style={{top: `${(pos * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
                  ))}
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret-last">
                  </div>
                </div>
                <div className="string">
                  {frets.map((pos, i) => (
                    chord.chordShapes[0].frets[4] != 0 && 
                    chord.chordShapes[0].frets[4] === pos && 
                    <div className="dot" style={{top: `${(pos * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
                  ))}
                  {frets.map((pos, i) => (
                    chord.chordShapes[0].frets[5] != 0 && 
                    chord.chordShapes[0].frets[5] === pos && 
                    <div className="dot-two" style={{top: `${(pos * STRING_Y_POS_SCALE) + (STRING_Y_POS_SCALE / 2)}px`}}></div>
                  ))}
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret">
                  </div>
                  <div className="fret-last">
                  </div>
                </div>
              </div>
            </div>
            <div className="chord-display-label">
                <div>E</div>
                <div>A</div>
                <div>D</div>
                <div>G</div>
                <div>B</div>
                <div>E</div>
              </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App
