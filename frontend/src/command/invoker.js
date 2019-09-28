class Invoker {
    constructor(maxHistorySize) {
        this.commands = new Array(maxHistorySize);
        this.maxHistorySize = maxHistorySize;
        this.lastInstrInd = -1;
    }

    invoke(command) {
        var result = command.execute();
        this.lastInstrInd++;

        if (this.lastInstrInd >= this.maxHistorySize) {
            this.commands.shift();
            this.lastInstrInd--;
        }

        this.commands[this.lastInstrInd] = command;

        return result;
    }

    undo() {
        if (this.lastInstrInd === -1) return;
        var result = this.commands[this.lastInstrInd].undo();
        this.lastInstrInd--;

        return result;
    }

    redo() {
        if (this.lastInstrInd + 1 >= this.maxHistorySize) return;
        this.lastInstrInd++;
        if (!this.commands[this.lastInstrInd]) {
            this.lastInstrInd--;
            return;
        }

        return this.commands[this.lastInstrInd].execute();
    }
}


const invoker = new Invoker(20);

export default invoker;