import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {HttpErrorResponse} from "@angular/common/http";
import {errorsConstant} from "../../environment/errorsConstant";

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  constructor(private toastr: ToastrService) {
  }


  handleHttpError(error: HttpErrorResponse) {
    if (error.status === errorsConstant.BAD_REQUEST.CODE) {
      this.toastr.error(errorsConstant.BAD_REQUEST.MESSAGE);
    } else if (error.status === errorsConstant.UNAUTHORIZED.CODE) {
      this.toastr.error(errorsConstant.UNAUTHORIZED.MESSAGE);
    } else if (error.status === errorsConstant.NOT_FOUND.CODE) {
      this.toastr.error(errorsConstant.NOT_FOUND.MESSAGE);
    } else if (error.status === errorsConstant.INTERNAL_SERVER_ERROR.CODE) {
      this.toastr.error(errorsConstant.INTERNAL_SERVER_ERROR.MESSAGE);
    } else {
      this.toastr.error(errorsConstant.UNKNOWN.MESSAGE);
    }
  }


}
