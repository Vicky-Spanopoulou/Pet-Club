
export interface IUser{
    id:number,
    firstName:string,
    lastName:string,
    username:string,
    email:string,
    password:string,
    phone:string,
    img:string,
    address: IAddress,
    notLocked: boolean,
    verified: boolean,
    role:string,
    sub_until: Date,
    advForEver: boolean
    transactions:ITransaction[],
    pets:IPet[],
    invoiceDetails: IInvoiceDetails,
    ads: IAd[],
    reminders: IReminder[],
    business: IBusiness,
    authorities: string[],
    active: boolean    
}


export interface IPet{
    
    id:number,
    name:string,
    breed:string,
    dob:Date,
    chipNumber:number,
    color: string,
    behavior: string,
    sex: string,
    image: string,
    petCategory:IPetCategory,
    examinations:IExamRecord[]
}

export interface IAdEdit{
    id:number,
    petName:string,
    chipNumber:number,
    sex:string,
    adCategory:string,
    someWords:string,
    descriptions:string,
    postDate: Date,
    image:string,
    user: number,
    petCategory: string,
    addressId:number
    street: string,
    longitude: number,
    latitude: number,
    postalCode: number,
    city: string
}

export interface IAd{
    id:number,
    petName:string,
    chipNumber:number,
    sex:string,
    adCategory:string,
    someWords:string,
    descriptions:string,
    postDate: Date,
    image:string,
    user: number,
    petCategory: IPetCategory,
    address:IAddress,
    lostDate: Date
}

export interface IBusiness{
    id:number,
    name:string,
    email:string,
    phone:string,
    description:string,
    text:string,
    imgLogo:string,
    imgBackground:string,
    facebook:string,
    instagram:string,
    pageUrl:string,
    address:IAddress,
    status:string,
    businessCategory:IBusinessCategory,
    avgRating: number;
}

export interface ICity{
    id: number | null,
    name: string | null,
    latitude: string | null,
    longitude: string | null
}

export interface IAddress{
    id: number,
    street: string,
    latitude: string,
    longitude: string,
    postalCode: string,
    city: ICity
}

export interface IPetCategory{
    id:number,
    name:string
}

// export interface IAdCategory{
//     id:number,
//     name:string
// }

export interface IBusinessCategory{
    id:number,
    name:string
}

export interface IReminder{
    id:number,
    info:string,
    done:boolean
}

export interface IInvoiceDetails{
    id: number,
    companyName: string,
    companyEmail: string,
    financialService: string,
    vatNumber: number,
    address: IAddress,
    phone: string
}

export interface IRating{
    id: number|null,
    ratingNumber: number,
    userId: number,
    businessId: number
}

export interface ISubscription{
    id:number,
    name:string,
    price:number,
    duration:number,
    role:string,
    queue:number,
    advForEver:boolean
}

export interface IExamRecord{
    id: number,
    examDay: Date,
    validUntil: Date,
    diagnosis: string,
    pet: IPet|null,
    user: IUser|null,
    examCat: string
}

export interface ITransaction{
    id: number|null,
    date: Date,
    type: string,
    paypalId: string,
    paid: boolean,
    sub_name: string,
    sub_price: string,
    sub_duration: number,
    sub_role: string,
    userId: number,
    advForEver: boolean
}

export interface IEmail{
    sender:string|null,
    receiver:string|null,
    subject:string|null,
    message:string|null
}
