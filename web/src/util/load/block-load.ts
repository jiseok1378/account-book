export const blockDuplicationLoad = async (loadVal: boolean, callback : () => Promise<void>) => {
    loadVal = true;
    await callback();
    loadVal = false;
  };