import { Pipe, PipeTransform } from '@angular/core';
// import moment from 'moment';

@Pipe({
  name: 'dateTimeFormat',
  pure: false,
})
export class DateTimeFormatPipe implements PipeTransform {

  transform(date: string, format: string = 'YYYY-MM-DD HH:mm:ss'): string {
     return 'a';//moment(date, format).fromNow();
  }

}
