import React from 'react';
import ReactDOM from 'react-dom';

class TodoApp extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            items: [],
            inputValue: ''
        }
        this.handleInputChange = this.handleInputChange.bind(this); // Ajoutez cette ligne

    }

    nbTasks() {
        return this.state.items.length;
    }

    remainingTasks() {
        return this.state.items.filter(item => !item.done).length;
    }


    addTask() {
        this.setState(prevState => ({
            items: [...prevState.items, {text: "jsp", done: false}]
        }));
    }

    toggleDone(index) {
        this.setState(prevState => ({
            items: prevState.items.map((item, i) => {
                if (i === index) {
                    return {
                        ...item,
                        done: !item.done
                    }
                }
                return item;
            })
        }));
    }

    handleInputChange(event) { // Ajoutez cette méthode
        this.setState({ inputValue: event.target.value });
    }

    render() {
        return (
            <div>
                <button id="addBtn" onClick={() => this.addTask()}>+</button>
                <input type="text" id="task" onChange={this.handleInputChange} value={this.state.inputValue} /> {/* Modifiez cette ligne */}
                <h2>Todos:</h2>
                <ol>
                    {this.state.items && this.state.items.map((item, index) => (
                        <li key={index}>
                            <label>
                                <input type="checkbox" onChange={() => this.toggleDone(index)} checked={item.done}/>
                                <span className={item.done ? "done" : ""}>{item.inputValue}</span>
                            </label>
                        </li>
                    ))}
                </ol>
                <span>Il y a {this.nbTasks()} tâche{this.nbTasks() <= 1 ? "" : "s"}. Il reste {this.remainingTasks()} tâche{this.nbTasks() <= 1 ? "" : "s"}</span>
            </div>
        )
    }
}

export default TodoApp;
