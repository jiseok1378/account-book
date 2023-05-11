
export const isEmpty = (value : any) : boolean => {
    if( value === undefined || value === null ){
        return true;
    }
    else if(Array.isArray(value)){
        return (value as any[]).length == 0
    }
    else if(typeof(value) === 'object'){
        return Object.keys(value).length == 0
    }
    else{
        return false;
    }
};

export const isNonEmpty = ( value : any ) => {
    return !isEmpty( value );
}
