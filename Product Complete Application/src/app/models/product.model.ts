export class Product{
    id: number;
    name : string;
    category : string;
    cost : number;

    constructor(name:string, category:string, cost:number){
        this.name = name;
        this.category = category;
        this.cost = cost;
    }
}