import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'replacerole'
})
export class ReplacerolePipe implements PipeTransform {

  transform(value: string): string {
   return value.replace('ROLE_','');
  }

}
