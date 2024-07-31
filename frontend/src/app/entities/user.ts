export class User {


  constructor(
    public id?: number ,
    public login?: string ,
    public role?: string ,
    public active?: boolean  ,
    public authStatus?: string ,
    public password?: string ,
  ) {
  }

}
